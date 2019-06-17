package com.wp.modules.fdfs.service;

import java.io.InputStream;

/**
 * @author Murphy
 * @description
 * @time 2019/5/23 14:31
 */
public interface FdfsService {
    String uploadInputStream(InputStream inputStream, long fileSize, String fileExtName);

    String getFileUrl(String fullPath);
}
