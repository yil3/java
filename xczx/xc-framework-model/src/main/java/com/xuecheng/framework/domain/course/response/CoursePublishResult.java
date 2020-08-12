package com.xuecheng.framework.domain.course.response;

import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursePublishResult extends ResponseResult {
    private String previewUrl;
    public CoursePublishResult (ResultCode resultCode,String previewUrl){
        super(resultCode);
        this.previewUrl = previewUrl;
    }
}
