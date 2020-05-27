package com.ihrm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据响应对象
 *    {
 *      success ：是否成功
 *      code    ：返回码
 *      message ：返回信息
 *      //返回数据
 *      data：  ：{
 *
 *      }
 *    }
 */
@Data
@NoArgsConstructor
public class Result {

    private boolean success;//是否成功
    private Integer code;// 返回码
    private String message;//返回信息
    private Object data;// 返回数据

    public Result(ResultCode code) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
    }

    public Result(ResultCode code,Object data) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
        this.data = data;
    }

    public Result(ResultCode code,String message) {
        this.success = code.success;
        this.code = code.code;
        this.message = message;
    }

    public Result(Integer code,String message,boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public Result(String message) {
        this.message = message;
    }


    public static Result SUCCESS(){
        return new Result(ResultCode.SUCCESS);
    }

    public static Result SUCCESS(Object data){
        return new Result(ResultCode.SUCCESS, data);
    }

    public static Result ERROR(){
        return new Result(ResultCode.SERVER_ERROR);
    }

    public static Result ERROR(String message){
        return new Result(ResultCode.ERROR, message);
    }

    public static Result FAIL(){
        return new Result(ResultCode.FAIL);
    }

}
