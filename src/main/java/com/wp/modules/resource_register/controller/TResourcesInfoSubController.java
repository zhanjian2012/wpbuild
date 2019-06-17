package com.wp.modules.resource_register.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wp.common.utils.R;
import com.wp.modules.resource_register.entity.TResourcesInfoSub;
import com.wp.modules.resource_register.service.TResourcesInfoSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TResourcesInfoSubController
 * @Description 资源自信息控制器
 * @Author yuxi
 * @Date 2019/5/24 14:39
 * @Version 1.0
 **/

@RestController
@RequestMapping("/res/sub")
public class TResourcesInfoSubController
{
    @Autowired
    private TResourcesInfoSubService tResourcesInfoSubService;


    /**
     *@Description 判断服务有效地址是否可用
     *@Params [serviceAddr]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/5/24 15:13
     */
    @GetMapping("/serviceAddrStatus/{serviceAddr}")
    public R serviceAddrStatus(@PathVariable String serviceAddr)
    {
        //构造条件
        EntityWrapper<TResourcesInfoSub> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("service_addr", serviceAddr);
        List<TResourcesInfoSub> list = new ArrayList<>();
        //查询
        list = tResourcesInfoSubService.selectList(entityWrapper);
        if(list.size() == 0 || list == null) return R.error(1,"不可用!");
        return R.error(0, "可用！");
    }


}
