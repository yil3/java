package com.xuecheng.service.course;

import com.xuecheng.framework.model.response.Result;

public interface CoursePicService {
    Result addCoursePic(String courseId,String picId);

    Result findCoursePicList (String courseId);

    Result deleteCoursePic(String courseId);
}
