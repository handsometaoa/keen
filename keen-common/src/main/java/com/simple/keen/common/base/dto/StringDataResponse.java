package com.simple.keen.common.base.dto;


public class StringDataResponse extends Response<String> {

    public StringDataResponse() {
    }

    public StringDataResponse(Integer code, String message) {
        super(code, message);
    }

    public StringDataResponse(Integer code, String message, String data) {
        super(code, message, data);
    }
}
