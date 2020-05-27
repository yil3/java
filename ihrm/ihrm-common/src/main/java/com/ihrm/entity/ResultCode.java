package com.ihrm.entity;

/**
 * 公共的返回码
 *    返回码code：
 *      成功：200
 *      失败：400
 *      未登录：406
 *      未授权：403
 *      抛出异常：500
 */
public enum ResultCode {

    SUCCESS(true,200,"操作成功！"),
    FAIL(false,405,"操作失败"),
    UNAUTHORISE(false,403,"权限不足"),
    ERROR(false,400),

    //---用户操作返回码  2xx----,
    SERVER_ERROR(false,500,"抱歉，系统繁忙，请稍后重试！"),
    MOBILEORPASSWORDERROR(false,408,"用户名或密码错误");



    //---用户操作返回码  3xxxx----
    //---权限操作返回码----
    //---其他操作返回码----

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    ResultCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    ResultCode(boolean success,int code){
        this.success = success;
        this.code = code;
    }



    public boolean success() {
        return success;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

}
