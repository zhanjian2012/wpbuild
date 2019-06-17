package com.wp.modules.db_manager.service;

import com.baomidou.mybatisplus.service.IService;
import com.wp.common.utils.PageUtils;
import com.wp.modules.db_manager.entity.TTablesInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName TTablesInfoService
 * @Description 库表列表接口
 * @Author yuxi
 * @Date 2019/5/29 10:50
 **/
public interface TTablesInfoService extends IService<TTablesInfo>
{
    //分页查询
    PageUtils queryPage(Map<String, Object> params);

    //批量删除
    void deleteByIds(List<Long> ids);

    //根据数据库名字查询表数量，并每次增加一个返回
    Integer selectCountDb(String dbName);
}
