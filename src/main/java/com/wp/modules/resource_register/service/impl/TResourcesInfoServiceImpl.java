package com.wp.modules.resource_register.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wp.common.utils.DateUtils;
import com.wp.common.utils.PageUtils;
import com.wp.common.utils.Query;
import com.wp.modules.resource_register.dao.TResourcesInfoMapper;
import com.wp.modules.resource_register.entity.TResourcesInfo;
import com.wp.modules.resource_register.entity.TResourcesInfoSub;
import com.wp.modules.resource_register.service.TResourcesInfoService;
import com.wp.modules.resource_register.service.TResourcesInfoSubService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TResourcesInfoServiceImpl
 * @Description 库表资源接口实现
 * @Author yuxi
 * @Date 2019/5/22 17:44
 * @Version 1.0
 **/
@Service("tResourcesInfoService")
public class TResourcesInfoServiceImpl extends ServiceImpl<TResourcesInfoMapper, TResourcesInfo> implements
        TResourcesInfoService
{

    @Autowired
    private TResourcesInfoMapper tResourcesInfoMapper;

    @Autowired
    private TResourcesInfoSubService tResourcesInfoSubService;

    @Override
    public List<Map<String, Object>> queryIdsAndDbNames()
    {
        return tResourcesInfoMapper.queryIdsAndDbNames();
    }

    /**
     * 根据ID查询TResourcesInfo中数据
     * @param id
     * @return
     */
	@Override
	public TResourcesInfo detail(Long id) {
		TResourcesInfo info = this.selectById(id);
		if(info != null) {
			info.setSubs(tResourcesInfoSubService.selectList(new EntityWrapper<TResourcesInfoSub>()
                    .eq("resource_id", info.getResourceId())));
		}
		return info;
	}

    /**
     *@Description 分页查询
     *@Params [params, type]
     *@Return com.wp.common.utils.PageUtils
     *@Author yuxi
     *@Date 2019/5/27 15:31
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params, Integer type)
    {
        //type 1:库表2：接口否则文件
        EntityWrapper<TResourcesInfo> entityWrapper = new EntityWrapper<>();

        String typeStr = "";
        if(type == 1)
        {
            typeStr = "库表";
            //构造查询条件
            String resourceName = (String) params.get("resourceName");
            String databaseName = (String) params.get("databaseName");
            String tableName = (String) params.get("tableName");
            entityWrapper.like(StringUtils.isNotBlank(resourceName),"resource_name", resourceName)
                    .like(StringUtils.isNotBlank(databaseName),"database_name", databaseName)
                    .like(StringUtils.isNotBlank(tableName),"table_name", tableName)
                    .like("resource_type", typeStr);
        }
        else if(type == 2)
        {
            typeStr = "接口";
            //构造查询条件
            String serviceChinese = (String) params.get("serviceChinese");
            String serviceEnglish = (String) params.get("serviceEnglish");
            String accessType = (String) params.get("accessType");
            entityWrapper.like(StringUtils.isNotBlank(serviceChinese),"service_chinese", serviceChinese)
                    .like(StringUtils.isNotBlank(serviceEnglish),"service_english",
                            serviceEnglish)
                    .like(StringUtils.isNotBlank(accessType),"access_type", accessType)
                    .like("resource_type", typeStr);
        }
        else
        {
            typeStr = "文件";
            //构造查询条件
            String resourceName = (String) params.get("resourceName");
            String resourceCatalogueCode = (String) params.get("resourceCatalogueCode");
            String filePath = (String) params.get("filePath");
            if((String) params.get("startTime")!= null && (String) params.get("endTime") != null)
            {
                Date startTime =  DateUtils.stringToDate((String) params.get("startTime"),
                        DateUtils.DATE_TIME_PATTERN);
                Date endTime =  DateUtils.stringToDate((String) params.get("endTime"),
                        DateUtils.DATE_TIME_PATTERN);
                entityWrapper.between("create_time",startTime, endTime);
            }
            entityWrapper.like(StringUtils.isNotBlank(resourceName),"resource_name", resourceName)
                    .like(StringUtils.isNotBlank(resourceCatalogueCode),"resource_catalogue_code",
                            resourceCatalogueCode)
                    .like(StringUtils.isNotBlank(filePath),"file_path", filePath)
                    .like("resource_type", typeStr);

         }

        //查询
        Page<TResourcesInfo> page = this.selectPage(new Query<TResourcesInfo>(params).getPage(), entityWrapper);
        return new PageUtils(page);
    }
}
