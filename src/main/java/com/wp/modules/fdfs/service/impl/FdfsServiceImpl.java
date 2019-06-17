package com.wp.modules.fdfs.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.wp.modules.fdfs.service.FdfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author Murphy
 * @description
 * @time 2019/5/23 16:51
 */
@Service("fdfsService")
public class FdfsServiceImpl implements FdfsService {
    @Autowired
    private FastFileStorageClient storageClient;
    @Value("${fdfs.web-server-url}")
    private String webServerUrl;

    public String uploadInputStream(InputStream inputStream, long fileSize, String fileExtName) {
        StorePath storePath = null;
        try {
            storePath = storageClient.uploadFile(inputStream, fileSize, fileExtName, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return storePath == null ? null : storePath.getFullPath();
    }

    public String getFileUrl(String fullPath) {
        return webServerUrl + fullPath;
    }
}
