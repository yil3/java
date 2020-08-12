package com.xuecheng.manage_course.controller;

import com.xuecheng.service.course.CategoryService;
import com.xuecheng.framework.domain.course.ext.CategoryNode;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    CategoryService categoryService;

    @ApiOperation("课程分类查询")
    @GetMapping("/list")
    public CategoryNode findCategoryList(){
        return categoryService.findCategoryList();
    }
}
