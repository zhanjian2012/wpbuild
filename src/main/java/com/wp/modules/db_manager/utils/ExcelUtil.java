package com.wp.modules.db_manager.utils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wp.modules.db_manager.entity.TTablesInfo;
import com.wp.modules.db_manager.service.TTablesInfoService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ExcelUtil
 * @Description excel工具类
 * @Author yuxi
 * @Date 2019/6/3 16:17
 * @Version 1.0
 **/
public class ExcelUtil
{

    /**
     *@Description excel导入
     *@Params [file]
     *@Return java.util.List<java.lang.Object[]>
     *@Author yuxi
     *@Date 2019/6/4 15:49
     */
    public static List<Object[]> importExcel(MultipartFile file) throws IOException
    {
        List<TTablesInfo> infoList = new ArrayList<>();
        //保存数据
        List<Object[]> objList = new ArrayList<>();
        TTablesInfo tTablesInfo;
        //创建HSSFWorkbook对象
        Workbook wb = null;
        String filename = file.getOriginalFilename();
        //格式错误
        if (!filename.matches("^.+\\.(?i)(xls)$") && !filename.matches("^.+\\.(?i)(xlsx)$")) return null;
        //03版本
        if (!filename.matches("^.+\\.(?i)(xlsx)$"))
        {
            wb = new HSSFWorkbook(file.getInputStream());
        }
        else
        {
            wb = new XSSFWorkbook(file.getInputStream());
        }

        //判断是否是标准模板
        if(!isStandard(wb)) return null;
        //获取有多少个sheet
        int numOfSheet = wb.getNumberOfSheets();
        for (int i = 0; i < numOfSheet; i++)
        {
            Sheet sheet = wb.getSheetAt(i);
            //获取sheet中一共有多少行，遍历行（第一行是标题）
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            for (int j = 0; j < physicalNumberOfRows; j++)
            {
                if (j == 0)
                {
                    continue;//标题行
                }
                Row row = sheet.getRow(j);
                Object[] obj = new Object[row.getPhysicalNumberOfCells()];
                //设置objList索引为0
                int index = 0;
                //获取有多少列
                int physicalNumberOfCells = row.getPhysicalNumberOfCells();

                for (int k = 0; k < physicalNumberOfCells; k++)
                {
                    Cell cell = row.getCell(k);
                    obj[index] = cell.getStringCellValue();
                    index++;
                }
                objList.add(obj);
            }
        }
        return objList;
    }


    /**
     *@Description 保存数据
     *@Params [list]
     *@Return com.wp.modules.db_manager.entity.TTablesInfo
     *@Author yuxi
     *@Date 2019/6/4 15:30
     */
    public static List<TTablesInfo> saveInfos(List<Object[]> list, TTablesInfoService tTablesInfoService)
    {
        List<TTablesInfo> tablesInfoList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++)
        {
            TTablesInfo tTablesInfo = new TTablesInfo();
            //tableName存在
            if(!hasData((String)list.get(i)[1], tTablesInfoService))continue;
            //不存在，保存数据
            tTablesInfo.setTableName((String)list.get(i)[1]);
            tTablesInfo.setDatabaseName((String) list.get(i)[0]);
            tTablesInfo.setTableDesc((String)list.get(i)[2]);
            tTablesInfo.setIsDelete(1);
            tTablesInfo.setCreateTime(new Date());
            tablesInfoList.add(tTablesInfo);
        }
        return tablesInfoList;

    }


    /**
     *@Description 判断excel是不是标准模板
     *@Params [wb]
     *@Return boolean
     *@Author yuxi
     *@Date 2019/6/4 17:36
     */
    public static boolean isStandard(Workbook wb)
    {
        //获取有多少个sheet
        int numOfSheet = wb.getNumberOfSheets();
        for (int i = 0; i < numOfSheet; i++)
        {
            Sheet sheet = wb.getSheetAt(i);
            //获取sheet中第一行，遍历行（第一行是标题）
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            Row row = sheet.getRow(0);
            int physicalNumberOfCells = row.getPhysicalNumberOfCells();
            //如果不等于三列，不是标准模板
            if(physicalNumberOfCells != 3)return false;
            //如果有一列没有满足以下列名，不符合要求
            if(!row.getCell(0).toString().equals("数据库名称") || !(row.getCell(1).toString().equals("表名称")) ||
                    !(row.getCell(2).toString().equals("描述")))return false;
            //限制长度
            if(!(row.getCell(0).toString().length() > 50) || !(row.getCell(1).toString().length() > 50) ||
                    !(row.getCell(2).toString().length() > 2000))return false;
            return true;
        }
        return true;
    }
    /**
     *@Description 判断导入数据的表名是否重复，重复跳过
     *@Params [tableName]
     *@Return boolean
     *@Author yuxi
     *@Date 2019/6/4 16:38
     */
    public static boolean hasData(String tableName, TTablesInfoService tTablesInfoService)
    {
        EntityWrapper<TTablesInfo> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("table_name", tableName);
        //是否存在这个表名
        List<TTablesInfo> list = (List<TTablesInfo>) tTablesInfoService.selectList(entityWrapper);
        if(list == null || list.size() == 0) return true;
        return false;
    }
}
