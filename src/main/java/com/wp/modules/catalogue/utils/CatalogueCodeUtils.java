package com.wp.modules.catalogue.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

/**
 * @description: 资源目录code生成
 * @author: zhanjian
 * @date: 2019年6月3日 下午4:50:00
 * @version: v1.0
 */
public class CatalogueCodeUtils {

	private static String PARENTID = "210308";
	private static Map<String, String> idMap = new HashMap<>();
	
	private static int max = 1;
	
	/**
	 * @description: 生成目录分类ID
	 * @param parentId
	 * @return
	 * @throws
	 */
	public synchronized static String getcatalogueId(String parentId) {
		if(StringUtils.isEmpty(parentId)) {
			parentId = PARENTID;
		}
		return parentId + getRandom(4);
	}
	
	public synchronized static String getRandom(int times) {
		Random random = null;
		try {
			random = SecureRandom.getInstanceStrong();
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<times; i++) {
				sb.append(random.nextInt(10)); 
			}
			if(StringUtils.isEmpty(idMap.get(sb.toString()))) {
				return sb.toString();
			} else {
				return getRandom(4);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * @description: 生成目录分类ID
	 * @param parentId
	 * @return
	 * @throws
	 */
	public synchronized static String getcatalogueCode(String catalogueId) {
        String num = String.format("%06d", max);   
        max = Integer.parseInt(num) + 1;
		if(StringUtils.isEmpty(catalogueId)) {
			return getcatalogueId("") + "/" + num;
		} else {
			return catalogueId + "/" + num;
		}
	}
	
	public static void main(String[] args) {
//		System.out.println(getcatalogueId(""));
		for(int i=0; i<10; i++) {
			System.out.println(getcatalogueCode(""));
		}
	}
}
