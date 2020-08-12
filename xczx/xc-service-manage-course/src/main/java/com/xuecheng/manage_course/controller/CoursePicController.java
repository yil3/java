package com.xuecheng.manage_course.controller;

import com.xuecheng.framework.model.response.Result;
import com.xuecheng.service.course.CoursePicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "课程图片管理",tags = "课程图片管理接口")
@RestController
@RequestMapping("/course/coursepic")
public class CoursePicController {
    @Resource
    CoursePicService coursePicService;

    @ApiOperation("添加图片关联信息")
    @PostMapping("/add")
    public Result addCoursePic(@RequestParam("courseId") String courseId,@RequestParam("pic") String picId){
        return coursePicService.addCoursePic(courseId, picId);
    }

    @GetMapping("/list/{courseId}")
    public Result findCoursePicList (@PathVariable("courseId") String courseId){
        return coursePicService.findCoursePicList(courseId);
    }

    @DeleteMapping("/delete")
    public Result deleteCoursePic (@RequestParam("courseId") String courseId){
        return coursePicService.deleteCoursePic(courseId);
    }
}
