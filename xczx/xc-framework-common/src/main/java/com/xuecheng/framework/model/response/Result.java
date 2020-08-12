package com.xuecheng.framework.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result extends ResponseResult {

    private Object data;// 返回数据

    public Result(ResultCode code) {
        super(code);
    }
    public Result(ResultCode code, Object data) {
        super(code);
        this.data = data;
    }


    public static Result SUCCESS() {
        return new Result(CommonCode.SUCCESS);
    }
    public static Result SUCCESS(Object data) {
        return new Result(CommonCode.SUCCESS, data);
    }
    public static Result ERROR() {
        return new Result(CommonCode.SERVER_ERROR);
    }
    public static Result FAIL() {
        return new Result(CommonCode.FAIL);
    }

}
