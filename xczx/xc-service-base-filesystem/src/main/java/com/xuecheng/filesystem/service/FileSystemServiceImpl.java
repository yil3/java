package com.xuecheng.filesystem.service;

import com.alibaba.fastjson.JSON;
import com.xuecheng.filesystem.dao.FileSystemDao;
import com.xuecheng.framework.domain.filesystem.FileSystem;
import com.xuecheng.framework.domain.filesystem.response.FileSystemCode;
import com.xuecheng.framework.domain.filesystem.response.UploadFileResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.service.filesystem.FileSystemService;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class FileSystemServiceImpl implements FileSystemService {
    @Value("${xuecheng.fastdfs.connect_timeout_in_seconds}")
    Integer connect_timeout_in_seconds;
    @Value("${xuecheng.fastdfs.network_timeout_in_seconds}")
    Integer network_timeout_in_seconds;
    @Value("${xuecheng.fastdfs.charset}")
    String charset;
    @Value("${xuecheng.fastdfs.tracker_servers}")
    String tracker_servers;

    @Resource
    FileSystemDao fileSystemDao;

    @Override
    public UploadFileResult upload(MultipartFile multipartFile, String fileTag, String businessKey,
                                   String metadata) {
        if (multipartFile == null) ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_FILEISNULL);

        String fileId = this.upload(multipartFile);
        if (fileId == null) {
            ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_SERVERFAIL);
        }

        FileSystem fileSystem = new FileSystem();
        fileSystem.setFileId(fileId);
        fileSystem.setFilePath(fileId);
        fileSystem.setFiletag(fileTag);
        fileSystem.setFileName(multipartFile.getOriginalFilename());
        fileSystem.setBusinesskey(businessKey);
        fileSystem.setFileSize(multipartFile.getSize());
        fileSystem.setFileType(multipartFile.getContentType());
        if (metadata != null) {
            try {
                Map map = JSON.parseObject(metadata, Map.class);
                fileSystem.setMetadata(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        fileSystemDao.save(fileSystem);
        return new UploadFileResult(CommonCode.SUCCESS, fileSystem);
    }


    private String upload(MultipartFile multipartFile) {
        this.initFastDFS();
        try {
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
            byte[] bytes = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            assert originalFilename != null;
            String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            return storageClient1.upload_file1(bytes, ext, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initFastDFS() {
        try {
            ClientGlobal.initByTrackers(tracker_servers);
            ClientGlobal.setG_charset(charset);
            ClientGlobal.setG_connect_timeout(connect_timeout_in_seconds);
            ClientGlobal.setG_network_timeout(network_timeout_in_seconds);
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionCast.cast(FileSystemCode.FS_INTIFDFSERROR);
        }
    }
}
