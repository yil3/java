package com.xuecheng.manage_course.controller;

import com.xuecheng.framework.domain.course.ext.CourseView;
import com.xuecheng.framework.domain.course.response.CoursePublishResult;
import com.xuecheng.service.course.CourseViewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "课程静态视图")
@RestController
@RequestMapping("/course")
public class CourseViewController {
    @Resource
    CourseViewService courseViewService;

    @ApiOperation("获取课程详情静态化视图数据")
    @GetMapping("/courseview/{id}")
    public CourseView courseview(@PathVariable("id") String id){
        return courseViewService.getCourseView(id);
    }

    @ApiOperation("预览课程详情静态化页面")
    @PostMapping("/preview/{id}")
    public CoursePublishResult preview (@PathVariable("id") String id){
        return courseViewService.preview(id);
    }

    @ApiOperation("课程详情页面发布")
    @PostMapping("/publish/{id}")
    public CoursePublishResult publish (@PathVariable("id") String id){
        return courseViewService.publish(id);
    }
}
