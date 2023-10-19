package com.simple.keen.common.consts;

public enum ResultCode {

    OK(200, "success"),
    FAIL(500, "fail");

    private Integer code;
    private String message;


    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
