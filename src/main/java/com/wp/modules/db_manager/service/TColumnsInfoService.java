package com.wp.modules.db_manager.service;

import com.baomidou.mybatisplus.service.IService;
import com.wp.common.utils.PageUtils;
import com.wp.modules.db_manager.entity.TColumnsInfo;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName TColumnsInfoService
 * @Description 数据库表字段管理接口
 * @Author yuxi
 * @Date 2019/5/31 15:37
 **/

public interface TColumnsInfoService extends IService<TColumnsInfo>
{
    //分页查询
    PageUtils queryPage(Map<String, Object> params);
    //批量删除
    void deleteByIds(List<Long> ids);
}
