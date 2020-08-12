package com.xuecheng.manage_cms.controller;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.Result;
import com.xuecheng.manage_cms.service.CmsPageServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value="cms页面管理接口",tags = "cms页面管理接口，提供页面的增、删、改、查")
@RestController
@RequestMapping("/cms/page")
public class CmsPageController {

    @Resource
    CmsPageServiceImpl cmsPageServiceImpl;

    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    @GetMapping("/list/{page}/{size}")
    public Result findList(@PathVariable("page") int page, @PathVariable("size")int size, QueryPageRequest queryPageRequest) {
        return cmsPageServiceImpl.findList(page, size, queryPageRequest);
    }

    @ApiOperation("添加页面")
    @PostMapping("/save")
    public Result save(@RequestBody CmsPage cmsPage){
        return cmsPageServiceImpl.save(cmsPage);
    }

    @ApiOperation("根据id查找页面信息")
    @GetMapping("/find/{id}")
    public Result findById(@PathVariable("id") String id){
        CmsPage cmsPage = cmsPageServiceImpl.findById(id);
        return Result.SUCCESS(cmsPage);
    }

    @ApiOperation("修改页面")
    @PutMapping("/edit/{id}")
    public Result update(@PathVariable("id")String id, @RequestBody CmsPage cmsPage) {
        return cmsPageServiceImpl.update(id,cmsPage);
    }

    @ApiOperation("删除页面")
    @DeleteMapping("/del/{id}")
    public Result delete(@PathVariable("id") String id){
        return cmsPageServiceImpl.delete(id);
    }

    @ApiOperation("页面发布")
    @PostMapping("/postPage/{pageId}")
    public Result post(@PathVariable("pageId") String pageId){
        cmsPageServiceImpl.post(pageId);
        return Result.SUCCESS();
    }

    @ApiOperation("页面添加和更新")
    @PostMapping("/addAndUpdate")
    public Result addAndUpdate(@RequestBody CmsPage cmsPage){
        return cmsPageServiceImpl.addAndUpdate(cmsPage);
    }


    @ApiOperation("页面一键发布")
    @PostMapping("/postPageQuick")
    public Result postPageQuick(@RequestBody CmsPage cmsPage){
        return cmsPageServiceImpl.postPageQuick(cmsPage);
    }
}
