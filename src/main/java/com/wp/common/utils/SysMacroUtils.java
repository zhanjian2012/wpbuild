package com.wp.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.util.StringUtils;
import com.wp.modules.sys.entity.SysMacroEntity;

/**
 * @description: 数据字典工具类
 * @author: zhanjian
 * @date: 2019年6月4日 上午10:11:50
 * @version: v1.0
 */
public class SysMacroUtils {
	
	private SysMacroUtils() {}

	public static List<SysMacroEntity> parentSysMacroList = new ArrayList<>(); //parentId=0的字典项
	public static Map<String, List<SysMacroEntity>> childrenSysMacroMap = new HashMap<>(); //子的字典项
	
	/**
	 * @description: 根据名称获取value
	 * @param name
	 * @return
	 * @throws
	 */
	public static String getValue(String name, String parentId) {
		List<SysMacroEntity> list = childrenSysMacroMap.get(parentId);
		if(list != null && !list.isEmpty()) {
			for(SysMacroEntity sm : list) {
				if(sm != null && (name.equals(sm.getName()) || name.contains(sm.getName()))) {
					return sm.getValue();
				}
			}
		}
		return null;
	}
	
	/**
	 * @description: 获取整型值
	 * @param name
	 * @param parentId
	 * @return
	 * @throws
	 */
	public static Integer getIntValue(String name, String parentId) {
		String value = getValue(name, parentId);
		if(!StringUtils.isEmpty(value)) {
			return Integer.parseInt(value);
		}
		return null;
	}
	
}
