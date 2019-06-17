package com.wp.modules.db_manager.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wp.common.utils.PageUtils;
import com.wp.common.utils.Query;
import com.wp.modules.db_manager.dao.TColumnsInfoMapper;
import com.wp.modules.db_manager.entity.TColumnsInfo;
import com.wp.modules.db_manager.service.TColumnsInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TColumnsInfoServiceImpl
 * @Description 数据库表字段管理接口实现
 * @Author yuxi
 * @Date 2019/5/31 15:38
 * @Version 1.0
 **/

@Service
public class TColumnsInfoServiceImpl extends ServiceImpl<TColumnsInfoMapper, TColumnsInfo> implements
        TColumnsInfoService
{

    @Autowired
    private TColumnsInfoMapper tColumnsInfoMapper;

    //分页查询
    @Override
    public PageUtils queryPage(Map<String, Object> params)
    {
        String columnName = (String) params.get("columnName");
        String columnType = (String) params.get("columnType");
        EntityWrapper<TColumnsInfo> entityWrapper = new EntityWrapper<>();
        //构造条件
        entityWrapper.like(StringUtils.isNotBlank(columnName), "column_name", columnName)
                .like(StringUtils.isNotBlank(columnType), "column_type", columnType)
                .eq("is_delete", "1");

        Page<TColumnsInfo> page = this.selectPage(new Query<TColumnsInfo>(params).getPage(), entityWrapper);
        return new PageUtils(page);
    }

    //批量删除
    @Override
    public void deleteByIds(List<Long> ids)
    {
        tColumnsInfoMapper.deleteByIds(ids);
    }
}
