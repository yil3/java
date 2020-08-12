package com.xuecheng.manage_course.controller;

import com.xuecheng.service.course.CourseMarketService;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.model.response.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api("课程营销查看和修改")
@RestController
@RequestMapping("/course")
public class CourseMarketController {
    @Resource
    CourseMarketService courseMarketService;

    @ApiOperation("课程营销添加或修改")
    @PostMapping("/market/{id}")
    public Result saveAndEdit(@PathVariable("id") String id, @RequestBody CourseMarket courseMarket){
        return courseMarketService.saveAndEdit(id, courseMarket);
    }

    @ApiOperation("查询营销")
    @GetMapping("/market/find/{id}")
    public Result findById(@PathVariable("id") String id){
        return courseMarketService.findById(id);
    }
}
