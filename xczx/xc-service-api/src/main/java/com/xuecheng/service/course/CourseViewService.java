package com.xuecheng.service.course;

import com.xuecheng.framework.domain.course.ext.CourseView;
import com.xuecheng.framework.domain.course.response.CoursePublishResult;

public interface CourseViewService {
    // 获取课程静态化数据模型
    CourseView getCourseView(String id);
    // 课程预览
    CoursePublishResult preview(String id);
    // 课程发布
    CoursePublishResult publish (String id);
}
