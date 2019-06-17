package com.wp.modules.db_manager.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wp.common.utils.PageUtils;
import com.wp.common.utils.Query;
import com.wp.modules.db_manager.dao.TTablesInfoMapper;
import com.wp.modules.db_manager.entity.TTablesInfo;
import com.wp.modules.db_manager.service.TTablesInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TTablesInfoServiceImpl
 * @Description 库表列表接口实现
 * @Author yuxi
 * @Date 2019/5/29 10:52
 * @Version 1.0
 **/
@Service
public class TTablesInfoServiceImpl extends ServiceImpl<TTablesInfoMapper, TTablesInfo> implements TTablesInfoService
{

    @Autowired
    private TTablesInfoMapper tTablesInfoMapper;
    /**
     *@Description 库表列表分页查询
     *@Params [params]
     *@Return com.wp.common.utils.PageUtils
     *@Author yuxi
     *@Date 2019/5/29 11:34
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params)
    {
        String tableName = (String) params.get("tableName");
        String desc = (String) params.get("desc");
        EntityWrapper<TTablesInfo> entityWrapper = new EntityWrapper<>();
        //构造查询条件
        entityWrapper.like(StringUtils.isNotBlank(tableName), "table_name", tableName)
                .like(StringUtils.isNotBlank(tableName), "table_desc", desc)
                .eq("is_delete","1");
        //查询
        Page<TTablesInfo> page = this.selectPage(new Query<TTablesInfo>(params).getPage(), entityWrapper);
        return new PageUtils(page);
    }

    //根据id批量删除
    @Override
    @Transactional
    public void deleteByIds(List<Long> ids)
    {
        tTablesInfoMapper.deleteByIds(ids);
    }


    /**
     *@Description 根据数据库名字查询表数量，并每次增加一个返回
     *@Params [dbName]
     *@Return java.lang.Long
     *@Author yuxi
     *@Date 2019/5/29 15:10
     */
    @Override
    public Integer selectCountDb(String dbName)
    {
        return tTablesInfoMapper.selectCountDb(dbName);
    }


}
