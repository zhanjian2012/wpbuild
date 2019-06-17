package com.wp.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wp.modules.sys.dao.SysOrgDao;
import com.wp.modules.sys.entity.SysOrgEntity;
import com.wp.modules.sys.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Murphy
 * @description 组织机构
 * @time 2019/5/27 16:19
 */
@Service("sysOrgService")
public class SysOrgServiceImpl extends ServiceImpl<SysOrgDao, SysOrgEntity> implements SysOrgService {
	@Autowired
	private SysOrgDao sysOrgDao;

	/**
	 * 新增
	 * @param org
	 * @return
	 */
	@Override
	public boolean saveOrg(SysOrgEntity org) {
		org.setDeleted(false);
		int count = sysOrgDao.insert(org);
		return count > 0;
	}

	/**
	 * 根据id查询
	 * @param orgId
	 * @return
	 */
	@Override
	public SysOrgEntity getOrg(Long orgId) {
		return sysOrgDao.selectById(orgId);
	}

	/**
	 * 更新
	 * @param org
	 * @return
	 */
	@Override
	public boolean updateOrg(SysOrgEntity org) {
		int count = sysOrgDao.updateById(org);
		return count > 0;
	}

	/**
	 * 查询所有机构id
	 * @param parentId
	 * @return
	 */
	@Override
	public List<Long> listOrgChildren(Long parentId) {
		return sysOrgDao.listOrgChildren(parentId);
	}

	/**
	 * 递归查询所有子机构
	 * @param parentId
	 * @return
	 */
	@Override
	public List<Long> getAllOrgChildren(Long parentId) {
		List<Long> orgIds = new ArrayList<>();
		List<Long> parentIds = listOrgChildren(parentId);
		recursionOrgChildren(parentIds, orgIds);
		return orgIds;
	}

	@Override
	public List<SysOrgEntity> getOrgListRecursively() {
		List<SysOrgEntity> sysOrgEntityList = sysOrgDao.getChildren(0L);
		return setChildrenOfOrgList(sysOrgEntityList);
	}

	private List<SysOrgEntity> setChildrenOfOrgList(List<SysOrgEntity> sysOrgEntityList) {
		sysOrgEntityList.forEach(sysOrgEntity -> {
			List<SysOrgEntity> children = sysOrgDao.getChildren(sysOrgEntity.getOrgId());
			if (children != null && children.size() > 0) {
				setChildrenOfOrgList(children);
			}

			sysOrgEntity.setList(children);
		});

		return sysOrgEntityList;
	}

	/**
	 * 递归查询子机构
	 * @param parentIds
	 * @param result
	 */
	public void recursionOrgChildren(List<Long> parentIds, List<Long> result) {
		for (Long parentId : parentIds) {
			List<Long> ids = listOrgChildren(parentId);
			if (ids.size() > 0) {
				recursionOrgChildren(ids, result);
			}
			result.add(parentId);
		}
	}

}
