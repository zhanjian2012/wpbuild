package com.wp.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wp.modules.sys.entity.SysMacroEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Murphy
 * @description 字典
 * @time 2019/5/27 16:15
 */
@Mapper
public interface SysMacroDao extends BaseMapper<SysMacroEntity> {
    List<SysMacroEntity> queryMacro(String description);

}
