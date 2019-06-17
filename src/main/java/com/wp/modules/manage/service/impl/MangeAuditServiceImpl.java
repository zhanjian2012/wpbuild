package com.wp.modules.manage.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wp.modules.manage.dao.ManageAuditDao;
import com.wp.modules.manage.entity.ManageAuditEntity;
import com.wp.modules.manage.service.ManageAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Murphy
 * @description 审核流程
 * @time 2019/5/27 17:29
 */
@Service("manageAuditServiceImpl")
public class MangeAuditServiceImpl extends ServiceImpl<ManageAuditDao, ManageAuditEntity> implements ManageAuditService {
    @Autowired
    private ManageAuditDao manageAuditDao;

    @Override
    public boolean addManageAudit(ManageAuditEntity mangeAuditEntity) {
        return this.insert(mangeAuditEntity);
    }

    @Override
    public boolean updateManageAudit(ManageAuditEntity manageAuditEntity) {
        return this.updateById(manageAuditEntity);
    }

    @Override
    public boolean deleteManageAudit(Long manageAuditId) {
        return this.deleteById(manageAuditId);
    }

    @Override
    public Page<ManageAuditEntity> getManageAudit(Map<String, Object> params) {
        // 组装分页参数
        if (params.containsKey("pageNumber") || params.containsKey("pageSize")) {
            int pageNumber = (int)params.get("pageNumber");
            int pageSize = (int)params.get("pageSize");
            long offset =  (pageNumber - 1) * pageSize;
            params.put("offset", offset);
        }

        // 查询数据列表
        List<ManageAuditEntity> records = manageAuditDao.selectManageAuditEntities(params);

        // 查询数据总数
        long totalCount = manageAuditDao.selectManageAuditEntityCount(params);

        // 组装page
        Page<ManageAuditEntity> page = new Page<>();
        page.setRecords(records);
        page.setTotal(totalCount);

        return page;
    }
}
