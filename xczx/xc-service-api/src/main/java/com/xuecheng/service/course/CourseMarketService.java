package com.xuecheng.service.course;

import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.model.response.Result;

public interface CourseMarketService {

    Result saveAndEdit(String id, CourseMarket courseMarket);

    Result findById(String id);
}
