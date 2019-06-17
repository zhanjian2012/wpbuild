package com.wp.modules.db_manager.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wp.common.utils.PageUtils;
import com.wp.common.utils.Query;
import com.wp.modules.db_manager.dao.TDatabaseInfoMapper;
import com.wp.modules.db_manager.entity.TDatabaseInfo;
import com.wp.modules.db_manager.service.TDatabaseInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TDatabaseInfoServiceImpl
 * @Description 数据库管理表接口实现
 * @Author yuxi
 * @Date 2019/5/28 15:18
 * @Version 1.0
 **/
@Service
public class TDatabaseInfoServiceImpl extends ServiceImpl<TDatabaseInfoMapper, TDatabaseInfo> implements TDatabaseInfoService
{

    @Autowired
    private TDatabaseInfoMapper tDatabaseInfoMapper;


    /**
     *@Description 分页查询数据库列表
     *@Params [params]
     *@Return com.wp.common.utils.PageUtils
     *@Author yuxi
     *@Date 2019/5/28 15:55
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params)
    {
        String databaseName = (String) params.get("databaseName");
        String databaseType = (String) params.get("databaseType");
        String departId = (String) params.get("departId");
        EntityWrapper<TDatabaseInfo> entityWrapper = new EntityWrapper<>();
        //构造查询条件
        entityWrapper.like(StringUtils.isNotBlank(databaseName),"database_name", databaseName)
                .like(StringUtils.isNotBlank(databaseType),"database_type", databaseType)
                .like(StringUtils.isNotBlank(departId),"depart_id", departId)
                .eq("is_delete", "1");
        //查询
        Page<TDatabaseInfo> page = this.selectPage(new Query<TDatabaseInfo>(params).getPage(), entityWrapper);
        return new PageUtils(page);
    }



    /**
     *@Description 通过ids逻辑删除数据
     *@Params [ids]
     *@Return void
     *@Author yuxi
     *@Date 2019/5/28 16:41
     */
    @Override
    @Transactional
    public void deleteByIds(List<Long> ids)
    {
        tDatabaseInfoMapper.deleteByIds(ids);
    }
}
