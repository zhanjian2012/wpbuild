package com.wp.modules.resource_register.service;

import com.baomidou.mybatisplus.service.IService;
import com.wp.common.utils.PageUtils;
import com.wp.modules.resource_register.entity.TResourcesInfo;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName TResourcesInfoService
 * @Description 库表资源接口
 * @Author yuxi
 * @Date 2019/5/22 17:42
 **/
public interface TResourcesInfoService extends IService<TResourcesInfo>
{
    List<Map<String, Object>> queryIdsAndDbNames();
    
    /**
     * 根据ID查询TResourcesInfo中数据
     * @param id
     * @return
     */
    TResourcesInfo detail(Long id);

    //分页查询
    PageUtils queryPage(Map<String, Object> params, Integer type);
}
