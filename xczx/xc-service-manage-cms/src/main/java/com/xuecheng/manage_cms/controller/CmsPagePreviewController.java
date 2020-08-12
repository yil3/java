package com.xuecheng.manage_cms.controller;

import com.xuecheng.framework.web.BaseController;
import com.xuecheng.manage_cms.service.CmsPageServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Api(tags = "页面预览接口")
@Controller
public class CmsPagePreviewController extends BaseController {
    @Resource
    CmsPageServiceImpl cmsPageServiceImpl;

    /**
     * 预览页面
     * */
    @GetMapping("/cms/preview/{pageId}")
    public void preview (@PathVariable("pageId") String id) throws IOException {
        String pageHtml = cmsPageServiceImpl.getPageHtml(id);
        ServletOutputStream outputStream = response.getOutputStream();
        response.addHeader("Content-type","text/html;charset=utf-8");
        outputStream.write(pageHtml.getBytes(StandardCharsets.UTF_8));
    }
}
