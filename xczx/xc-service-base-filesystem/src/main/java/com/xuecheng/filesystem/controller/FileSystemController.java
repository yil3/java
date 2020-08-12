package com.xuecheng.filesystem.controller;

import com.xuecheng.framework.domain.filesystem.response.UploadFileResult;
import com.xuecheng.service.filesystem.FileSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Api(value = "文件系统接口",tags = "文件系统接口，提供增、删、查、改")
@RestController
@RequestMapping("/filesystem")
public class FileSystemController {
    @Resource
    FileSystemService fileSystemService;

    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public UploadFileResult upload(MultipartFile multipartFile, String fileTag, String businessKey,
                                   String metadata){
        return fileSystemService.upload(multipartFile, fileTag, businessKey, metadata);
    }
}
