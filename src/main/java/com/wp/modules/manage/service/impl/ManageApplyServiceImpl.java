package com.wp.modules.manage.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wp.modules.manage.dao.ManageApplyDao;
import com.wp.modules.manage.entity.ManageApplyEntity;
import com.wp.modules.manage.service.ManageApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Murphy
 * @description 已发布资源申请
 * @time 2019/5/27 17:28
 */
@Service("manageApplyServiceImpl")
public class ManageApplyServiceImpl extends ServiceImpl<ManageApplyDao, ManageApplyEntity> implements ManageApplyService {
    @Autowired
    private ManageApplyDao manageApplyDao;

    @Override
    public boolean addManageApply(ManageApplyEntity manageApplyEntity) {
        manageApplyEntity.setSuspend(false);
        manageApplyEntity.setDeleted(false);

        return this.insert(manageApplyEntity);
    }

    @Override
    public boolean updateManageApply(ManageApplyEntity manageApplyEntity) {
        return this.updateById(manageApplyEntity);
    }

    @Override
    public boolean suspendManageApply(Long[] applyIds) {
        long affectRows = manageApplyDao.suspendManageApplyBatch(applyIds);

        return affectRows == applyIds.length;
    }

    @Override
    public boolean deleteManageApplyBatch(Long[] applyIds) {
        long affectRows = manageApplyDao.deleteManageApplyBatch(applyIds);

        return affectRows == applyIds.length;
    }

    @Override
    public Page<ManageApplyEntity> getManageApply(Map<String, Object> params) {
        // 组装分页参数
        int pageNumber = (int)params.get("pageNumber");
        int pageSize = (int)params.get("pageSize");
        long offset =  (pageNumber - 1) * pageSize;
        params.put("offset", offset);

        // 查询数据列表
        List<ManageApplyEntity> records = manageApplyDao.selectManageApplyEntities(params);

        // 查询数据总数
        long totalCount = manageApplyDao.selectManageApplyEntityCount(params);

        // 组装page
        Page<ManageApplyEntity> page = new Page<>();
        page.setRecords(records);
        page.setTotal(totalCount);

        return page;
    }
}
