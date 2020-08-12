package com.xuecheng.manage_cms.controller;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.manage_cms.service.CmsPageServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "Cms配置管理器接口", tags = "Cms配置管理器接口，提供数据模型的管理、查询接口")
@RestController
@RequestMapping("/cms/config")
public class CmsConfigController {
    @Resource
    CmsPageServiceImpl cmsPageServiceImpl;

    /**
     * 获取模型数据
     * */
    @ApiOperation("根据id查找CMS配置信息")
    @GetMapping("/getmodel/{id}")
    public CmsConfig findById(@PathVariable("id") String id){
        return cmsPageServiceImpl.getModel(id);
    }


}
