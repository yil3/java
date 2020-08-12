package com.xuecheng.service.course;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.model.response.Result;


public interface CourseBaseService {

    TeachplanNode findTeachplanList(String courseId);

    Result addTeachplan(Teachplan teachplan);

    Result findCourseList(Integer page, Integer size, CourseListRequest courseListRequest);

    Result addCourseBase(CourseBase courseBase);

    Result findById(String courseId);

    Result editCourseBase(String courseId,CourseBase courseBase);
}
