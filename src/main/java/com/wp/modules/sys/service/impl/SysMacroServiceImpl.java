package com.wp.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wp.modules.sys.dao.SysLogDao;
import com.wp.modules.sys.dao.SysMacroDao;
import com.wp.modules.sys.entity.SysLogEntity;
import com.wp.modules.sys.entity.SysMacroEntity;
import com.wp.modules.sys.service.SysMacroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Murphy
 * @description 字典
 * @time 2019/5/27 16:38
 */
@Service("sysMacroServiceImpl")
public class SysMacroServiceImpl extends ServiceImpl<SysMacroDao, SysMacroEntity> implements SysMacroService {
    @Autowired
    private SysMacroDao sysMacroDao;
    @Override
    public List<SysMacroEntity> getSysMacros(String description) {
        return sysMacroDao.queryMacro(description);
    }
}
