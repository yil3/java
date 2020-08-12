package com.xuecheng.manage_course.dao;

import com.github.pagehelper.Page;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.domain.course.response.QueryCourseListResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CourseBaseMapper {
    CourseBase findCourseBaseById(String id);
    Page<QueryCourseListResult> findCourseList (CourseListRequest courseListRequest);
}
