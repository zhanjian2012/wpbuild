package com.wp.modules.db_manager.service;

import com.baomidou.mybatisplus.service.IService;
import com.wp.common.utils.PageUtils;
import com.wp.modules.db_manager.entity.TDatabaseInfo;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName TDatabaseInfoService
 * @Description 数据库管理表接口
 * @Author yuxi
 * @Date 2019/5/28 15:03
 **/
public interface TDatabaseInfoService extends IService<TDatabaseInfo>
{
    //分页查询
    PageUtils queryPage(Map<String, Object> params);

    //通过ids逻辑删除数据
    void deleteByIds(List<Long> ids);
}
