package com.wp.modules.db_manager.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wp.common.utils.IdsSplitUtil;
import com.wp.common.utils.PageUtils;
import com.wp.common.utils.R;
import com.wp.modules.db_manager.entity.TColumnsInfo;
import com.wp.modules.db_manager.service.TColumnsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TColumnsInfoController
 * @Description 数据库表字段管理接口
 * @Author yuxi
 * @Date 2019/5/31 15:41
 * @Version 1.0
 **/

@RestController
@RequestMapping("/column")
public class TColumnsInfoController
{

    @Autowired
    private TColumnsInfoService tColumnsInfoService;

    /**
     *@Description 分页
     *@Params [params]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/6/3 11:22
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params)
    {
        PageUtils pageUtils = tColumnsInfoService.queryPage(params);
        if(pageUtils.getList() == null || pageUtils.getList().size() == 0) return R.error(1, "无结果");
        return R.ok().put("data", pageUtils);
    }


    /**
     *@Description 根据id查询
     *@Params [id]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/6/3 11:22
     */
    @GetMapping("/column/{id}")
    public R column(@PathVariable Long id)
    {
        EntityWrapper<TColumnsInfo> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("col_id", id).eq("is_delete", "1");
        List<TColumnsInfo> list = tColumnsInfoService.selectList(entityWrapper);
        if(list == null || list.size() == 0) return R.error(1, "无结果！");
        return R.ok().put("data", list);
    }


    /**
     *@Description 编辑表字段信息
     *@Params [tColumnsInfo, id]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/6/3 11:30
     */
    @PutMapping("/column")
    public R column(TColumnsInfo tColumnsInfo, Long id)
    {
        tColumnsInfo.setColId(id);
        tColumnsInfoService.updateById(tColumnsInfo);
        return R.ok();
    }


    /**
     *@Description 批量删除
     *@Params [ids]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/6/3 11:36
     */
    @DeleteMapping("/column")
    public R column(String ids)
    {
        tColumnsInfoService.deleteByIds(IdsSplitUtil.strConvertList(ids));
        return R.ok();
    }


    /**
     *@Description 新增表字段
     *@Params [tColumnsInfo]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/6/3 14:12
     */
    @PostMapping("/column")
    public R column(TColumnsInfo tColumnsInfo)
    {
        tColumnsInfoService.insert(tColumnsInfo);
        return R.ok();
    }
}
