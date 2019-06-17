package com.wp.modules.oss.service;

import com.baomidou.mybatisplus.service.IService;
import com.wp.common.utils.PageUtils;
import com.wp.modules.oss.entity.SysOssEntity;

import java.util.Map;

/**
 * 文件上传
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-25 12:13:26
 */
public interface SysOssService extends IService<SysOssEntity> {

	PageUtils queryPage(Map<String, Object> params);
}
