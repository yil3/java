package com.xuecheng.manage_course.service;

import com.xuecheng.service.course.CategoryService;
import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.manage_course.dao.CategoryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;
    @Override
    public CategoryNode findCategoryList() {
        return categoryMapper.findCategoryList();
    }
}
