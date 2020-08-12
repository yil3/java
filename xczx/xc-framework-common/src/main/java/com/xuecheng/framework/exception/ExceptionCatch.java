package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Data
@ControllerAdvice
public class ExceptionCatch {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);

    private static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;
    protected static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();


    // 自定义异常处理
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public ResponseResult customException(CustomException customException) {
        // 错误日志记录
        LOGGER.error("Catch exception:{}", customException.getMessage());

        ResultCode resultCode = customException.getResultCode();
        return new ResponseResult(resultCode);
    }

    static {
        // 向map中添加 异常类对应的返回ResultCode
        builder.put(HttpMessageNotReadableException.class, CommonCode.INVALID_PARAM);
    }

    // 全局异常处理
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseResult exception(Exception exception) {
        // 错误日志记录
        LOGGER.error("Catch exception:{}", exception.getMessage());
        if (EXCEPTIONS == null) {
            EXCEPTIONS = builder.build();
        }
        // 根据异常类型查找map中对应的CommonCode，返回resultCode,无对应异常类返回全局处理
        ResultCode resultCode = EXCEPTIONS.get(exception.getClass());
        if (resultCode != null) {
            return new ResponseResult(resultCode);
        } else {
            // 返回全局异常处理
            return new ResponseResult(CommonCode.SERVER_ERROR);
        }
    }
}
