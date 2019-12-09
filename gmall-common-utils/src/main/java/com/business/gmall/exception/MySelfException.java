package com.business.gmall.exception;


//自定义runtime异常，如果出问题直接抛出
public class MySelfException extends RuntimeException{
    //自定状态和信息
    private Integer code;
    private String message;

    public MySelfException(Integer code,String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
