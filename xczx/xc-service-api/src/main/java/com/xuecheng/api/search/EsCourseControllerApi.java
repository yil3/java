package com.xuecheng.api.search;

import com.xuecheng.framework.domain.course.CoursePub;
import com.xuecheng.framework.domain.search.CourseSearchParam;
import com.xuecheng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "课程搜索接口")
public interface EsCourseControllerApi {
    @ApiOperation("课程综合搜索")
    QueryResponseResult<CoursePub> list(Integer page, Integer size, CourseSearchParam courseSearchParam);
}
