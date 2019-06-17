package com.wp.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.wp.modules.sys.entity.SysMacroEntity;

import java.util.List;

public interface SysMacroService extends IService<SysMacroEntity> {
    List<SysMacroEntity> getSysMacros(String description);
}
