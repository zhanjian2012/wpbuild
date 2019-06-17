package com.wp.modules.db_manager.controller;

import com.wp.common.utils.IdsSplitUtil;
import com.wp.common.utils.PageUtils;
import com.wp.common.utils.R;
import com.wp.modules.db_manager.entity.TDatabaseInfo;
import com.wp.modules.db_manager.service.TDatabaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @ClassName TDatabaseInfoController
 * @Description 数据库管理表控制器
 * @Author yuxi
 * @Date 2019/5/28 15:19
 * @Version 1.0
 **/

@RestController
@RequestMapping("/database")
public class TDatabaseInfoController
{
    @Autowired
    private TDatabaseInfoService tDatabaseInfoService;


    /**
     *@Description 分页查询数据库列表
     *@Params [params]
     *@Return com.wp.common.utils.PageUtils
     *@Author yuxi
     *@Date 2019/5/28 15:55
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params)
    {
        PageUtils pageUtils = tDatabaseInfoService.queryPage(params);
        return R.ok().put("data", pageUtils);
    }


    /**
     *@Description 新增数据库
     *@Params [tDatabaseInfo]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/5/28 16:06
     */
    @PostMapping("/data")
    public R data(TDatabaseInfo tDatabaseInfo)
    {
        tDatabaseInfo.setCreateTime(new Date());
        tDatabaseInfo.setIsDelete(1);
        tDatabaseInfo.setTablesCnt(0);
        tDatabaseInfo.setStatus(0);
        tDatabaseInfoService.insert(tDatabaseInfo);
        return R.ok();
    }


    /**
     *@Description 通过ids逻辑删除数据
     *@Params [ids]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/5/28 17:06
     */
    @DeleteMapping("/data")
    public R data(String ids)
    {
        tDatabaseInfoService.deleteByIds(IdsSplitUtil.strConvertList(ids));
        return R.ok();
    }


    /**
     *@Description 修改数据
     *@Params [ids]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/5/28 17:06
     */
    @PutMapping("/data")
    public R upData(TDatabaseInfo tDatabaseInfo)
    {
        tDatabaseInfoService.updateById(tDatabaseInfo);
        return R.ok();
    }


    /**
     *@Description 根据id查询信息
     *@Params [ids]
     *@Return com.wp.common.utils.R
     *@Author yuxi
     *@Date 2019/5/28 17:06
     */
    @GetMapping("/data/{id}")
    public R data(@PathVariable("id") Long databaseId)
    {
        return R.ok().put("data", tDatabaseInfoService.selectById(databaseId));
    }
}
