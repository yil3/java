package com.xuecheng.manage_course.controller;

import com.xuecheng.service.course.CourseBaseService;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.model.response.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@Api(value = "课程管理接口",tags = "课程管理接口提供增、删、查、改")
@RestController
@RequestMapping("/course")
public class CourseBaseController {
    @Resource
    CourseBaseService courseBaseService;

    @ApiOperation("节点列表查询")
    @GetMapping("/teachplan/list/{courseId}")
    public TeachplanNode findTeachplanList(@PathVariable("courseId") String courseId){
        return courseBaseService.findTeachplanList(courseId);
    }

    @ApiOperation("添加课程计划")
    @PostMapping("/teachplan/add")
    public Result addTeachplan (@RequestBody Teachplan teachplan) {
        return courseBaseService.addTeachplan(teachplan);
    }

    @ApiOperation("课程列表分页查询")
    @GetMapping("/coursebase/list/{page}/{size}")
    public Result findCourseList (@PathVariable("page") Integer page,
                                  @PathVariable("size") Integer size,
                                  CourseListRequest courseListRequest){
        return courseBaseService.findCourseList(page,size,courseListRequest);
    }

    @ApiOperation("添加课程")
    @PostMapping("/coursebase/add")
    public Result addCourseBase (@RequestBody CourseBase courseBase){
        return courseBaseService.addCourseBase(courseBase);
    }

    @ApiOperation("根据id查询课程")
    @GetMapping("/coursebase/find/{courseId}")
    public Result findById(@PathVariable("courseId") String courseId){
        return courseBaseService.findById(courseId);
    }

    @ApiOperation("修改课程信息")
    @PutMapping("/coursebase/edit/{courseId}")
    public Result editCourse(@PathVariable String courseId, @RequestBody CourseBase courseBase){
        return courseBaseService.editCourseBase(courseId, courseBase);
    }

}
