package com.wp.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wp.modules.sys.entity.SysOrgEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Murphy
 * @description 组织机构
 * @time 2019/5/27 16:16
 */
@Mapper
public interface SysOrgDao extends BaseMapper<SysOrgEntity> {

	/**
	 * 统计子机构数量
	 * @param parentId
	 * @return
	 */
	int countOrgChildren(Long parentId);

	/**
	 * 查询子机构ID集合
	 * @param parentId
	 * @return
	 */
	List<Long> listOrgChildren(Long parentId);

	/**
	 * 查询所有子部门
	 * @param parentId
	 * @return
	 */
	List<SysOrgEntity> getChildren(Long parentId);

}
