package com.wp.modules.db_manager.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wp.common.utils.IdsSplitUtil;
import com.wp.common.utils.PageUtils;
import com.wp.common.utils.R;
import com.wp.modules.db_manager.entity.TDatabaseInfo;
import com.wp.modules.db_manager.entity.TTablesInfo;
import com.wp.modules.db_manager.service.TDatabaseInfoService;
import com.wp.modules.db_manager.service.TTablesInfoService;
import com.wp.modules.db_manager.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TTablesInfoController
 * @Description 库表列表控制器
 * @Author yuxi
 * @Date 2019/5/29 10:54
 * @Version 1.0
 **/
@RestController
@RequestMapping("/table")
public class TTablesInfoController
{
    @Autowired
    private TTablesInfoService tTablesInfoService;
    @Autowired
    private TDatabaseInfoService tDatabaseInfoService;


    @GetMapping("/importExcel")
    public R upload(@RequestParam("fileName") MultipartFile file) throws IOException
    {
        List<Object[]> obj = ExcelUtil.importExcel(file);
        if(obj == null || obj.size() == 0) return R.error(1, "导入模板文件类型或格式有误！");
        //解析数据
        List<TTablesInfo> tablesInfoList = ExcelUtil.saveInfos(obj, tTablesInfoService);
        //数据库中已经存在导入的表名
        if(tablesInfoList == null || tablesInfoList.size() == 0) return R.error(1, "导入的表名已存在或没有数据！");
        tTablesInfoService.insertBatch(tablesInfoList);
        return R.ok();
    }

    /**
     *@Description 库表列表分页查询
     *@Params [params]
     *@Return com.wp.common.utils.PageUtils
     *@Author yuxi
     *@Date 2019/5/29 11:34
     */
    @GetMapping("/tableInfos")
    public R tableInfos(@RequestParam Map<String, Object> params)
    {
        PageUtils page = tTablesInfoService.queryPage(params);
        return R.ok().put("data", page);
    }

    /**
     *@Description 根据id批量删除
     *@Params [ids]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/5/29 11:45
     */
    @DeleteMapping("/table")
    public R delInfos(String ids)
    {
        tTablesInfoService.deleteByIds(IdsSplitUtil.strConvertList(ids));
        return R.ok();
    }



    /**
     *@Description 逐条添加表数据(添加同时更新t_database_info中对应库下的表数量)
     *@Params [dbName, tableName, desc]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/5/29 15:49
     */
    @PostMapping("/table")
    public R saveInfos(@RequestParam("dbName") String dbName,
                        @RequestParam("tableName") String tableName,
                        @RequestParam("desc") String desc
                        )
    {
        TTablesInfo tTablesInfo = new TTablesInfo();
        TDatabaseInfo tDatabaseInfo = new TDatabaseInfo();
        //添加表信息
        tTablesInfo.setDatabaseName(dbName);
        tTablesInfo.setCreateTime(new Date());
        tTablesInfo.setTableDesc(desc);
        tTablesInfo.setIsDelete(1);
        tTablesInfoService.insert(tTablesInfo);
        EntityWrapper<TDatabaseInfo> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("database_name", dbName);
        //更新t_database_info中对应表数量
        tDatabaseInfo.setDatabaseName(dbName);
        tDatabaseInfo.setTablesCnt(tTablesInfoService.selectCountDb(dbName));
        tDatabaseInfoService.update(tDatabaseInfo, entityWrapper);
        return R.ok();
    }


    /**
     *@Description 根据id查询信息
     *@Params [table_id]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/5/29 16:03
     */
    @GetMapping("/table/{id}")
    public R tables(@PathVariable("id") Long table_id)
    {
        return R.ok().put("data", tTablesInfoService.selectById(table_id));
    }


    /**
     *@Description 更新一条记录
     *@Params [tTablesInfo, tableId]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/5/29 16:27
     */
    @PutMapping("/table")
    public R update(TTablesInfo tTablesInfo)
    {
        tTablesInfo.setUpdateTime(new Date());
        tTablesInfoService.updateById(tTablesInfo);
        return R.ok();
    }
}
