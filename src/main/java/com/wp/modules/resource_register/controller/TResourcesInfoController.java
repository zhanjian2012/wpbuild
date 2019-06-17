package com.wp.modules.resource_register.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wp.common.utils.PageUtils;
import com.wp.common.utils.R;
import com.wp.modules.fdfs.service.FdfsService;
import com.wp.modules.resource_register.entity.TResourcesInfo;
import com.wp.modules.resource_register.entity.TResourcesInfoSub;
import com.wp.modules.resource_register.service.TResourcesInfoService;
import com.wp.modules.resource_register.service.TResourcesInfoSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TResourcesInfoController
 * @Description 库表资源控制器
 * @Author yuxi
 * @Date 2019/5/22 17:38
 * @Version 1.0
 **/

@RestController
@RequestMapping("/res")
public class TResourcesInfoController
{
    @Autowired
    private TResourcesInfoService tResourcesInfoService;
    @Autowired
    private TResourcesInfoSubService tResourcesInfoSubService;
    @Autowired
    private FdfsService fdfsService;


    /**
     *@Description 分页查询
     *@Params [params, type]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/6/5 16:18
     */
    @GetMapping("/list/{type}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable Integer type)
    {
        //type 1:库表2：接口否则文件
        PageUtils page = tResourcesInfoService.queryPage(params, type);
        return R.ok().put("page", page);
    }

    /**
     *@Description 资源注册
     *@Params [tResourcesInfo, type]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/5/24 15:11
     */
    @PostMapping("/resources")
    public R resources(TResourcesInfo tResourcesInfo, @RequestParam("type") Integer type,
                       @RequestParam(required = false) TResourcesInfoSub tResourcesInfoSub,
                        @RequestParam("fileName") MultipartFile file
                        ) throws IOException
    {
        //附件
        String path = file.getOriginalFilename();
        String fullPath = fdfsService.uploadInputStream(file.getInputStream(), file.getSize(), path);
        String savePath = fdfsService.getFileUrl(fullPath);
        //type 1:库表2：接口否则文件
        if(type == 1)
        {
            //资源类型 (库表、接口、文件)
            tResourcesInfo.setResourceType("库表");
        }
        else if(type == 2)
        {
            //资源类型 (库表、接口、文件)
            tResourcesInfo.setResourceType("接口");
            //创建时间
            tResourcesInfo.setCreateTime(new Date());
            //是否删除
            tResourcesInfo.setIsDelete(1);
            //发布状态
            tResourcesInfo.setStatus(0);
            //添加资源信息
            tResourcesInfoService.insert(tResourcesInfo);
            tResourcesInfoSub.setResourceId(tResourcesInfo.getResourceId());
            //添加子资源信息
            tResourcesInfoSubService.insert(tResourcesInfoSub);
            return R.ok();
        }
        else
        {
            //资源类型 (库表、接口、文件)
            tResourcesInfo.setResourceType("文件");
        }
        //创建时间
        tResourcesInfo.setCreateTime(new Date());
        //是否删除
        tResourcesInfo.setIsDelete(1);
        //发布状态
        tResourcesInfo.setStatus(0);
        //附件路径
        tResourcesInfo.setFilePath(savePath);
        //添加资源信息
        tResourcesInfoService.insert(tResourcesInfo);

        return R.ok();
    }


    /**
     *@Description 查询所有资源id和数据库名称
     *@Params []
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/5/23 14:39
     */
    @GetMapping("/dbNames")
    public R dbNames()
    {
        List<Map<String, Object>> list = tResourcesInfoService.queryIdsAndDbNames();
        return R.ok().put("data", list);
    }


    /**
     *@Description 根据资源id（表名）查询所属子表
     *@Params [resourceId]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/5/23 16:35
     */
    @GetMapping("/tableInfos/{resourceId}")
    public R tableInfos(@PathVariable Long resourceId)
    {
        //构造查询条件
        EntityWrapper<TResourcesInfoSub> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("resource_id", resourceId);
        List<TResourcesInfoSub> list = tResourcesInfoSubService.selectList(entityWrapper);
        if(list == null || list.size() == 0) return R.error(1,"无结果");
        return R.ok().put("data", list);
    }


    /**
     *@Description 根据资源子表id（表名）查询信息
     *@Params [resourceId]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/5/23 16:35
     */
    @GetMapping("/subTableInfos/{subItemId}")
    public R subTableInfos(@PathVariable Long subItemId)
    {
        //构造查询条件
        EntityWrapper<TResourcesInfoSub> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("sub_item_id", subItemId);
        List<TResourcesInfoSub> list = tResourcesInfoSubService.selectList(entityWrapper);
        if(list == null || list.size() == 0) return R.error(1,"无结果");
        return R.ok().put("data", list);
    }

    /**
     *@Description 更新库表资源子表
     *@Params [tResourcesInfoSub]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/5/23 17:42
     */
    @PutMapping("/subTableInfos")
    public R tableInfos(TResourcesInfoSub tResourcesInfoSub)
    {
        tResourcesInfoSub.setUpdateTime(new Date());
        tResourcesInfoSubService.updateById(tResourcesInfoSub);
        return R.ok();
    }


    /**
     *@Description 根据id查询资源详细信息
     *@Params [id]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/5/27 17:09
     */
    @GetMapping("/resourceInfos/{id}")
    public R resourceInfosById(@PathVariable Long id)
    {
        return R.ok().put("data", tResourcesInfoService.detail(id));
    }
}
