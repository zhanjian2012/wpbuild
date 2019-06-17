package com.wp.config;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wp.common.utils.SysMacroUtils;
import com.wp.modules.catalogue.utils.QueryField;
import com.wp.modules.sys.entity.SysMacroEntity;
import com.wp.modules.sys.service.SysMacroService;

/**
 * @description: 系统启动时加载字典表数据
 * @author: zhanjian
 * @date: 2019年4月17日 下午4:34:34
 * @version: v1.0
 */
@Component
public class SystemStartupConfig  implements CommandLineRunner {
	
	private Logger log = LoggerFactory.getLogger(SystemStartupConfig.class);

    @Autowired
    private SysMacroService sysMacroService;
	
	@Override
	public void run(String... args) throws Exception {
		log.info("系统启动，开始加载字典表数据");
		
		List<SysMacroEntity> list = sysMacroService.selectList(new EntityWrapper<SysMacroEntity>().eq("parent_id", 0).eq(QueryField.STATUS, 1));
		if(list != null && !list.isEmpty()) {
			SysMacroUtils.parentSysMacroList = list;
			for(SysMacroEntity sm : list) {
				List<SysMacroEntity> child = sysMacroService.selectList(new EntityWrapper<SysMacroEntity>().eq("parent_id", sm.getMacroId()).eq(QueryField.STATUS, 1));
				SysMacroUtils.childrenSysMacroMap.put(sm.getMacroId().toString(), child);
			}
		}
		
		log.info("系统启动，开始加载字典表数据完毕");
	}

}
