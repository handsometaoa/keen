package com.simple.keen.common.base.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Response<T extends Serializable> implements Serializable {
    protected Integer code;
    protected String message;
    protected T data;
    protected Map<String, Serializable> extra = new HashMap<>();

    public Response() {
    }

    public Response(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, Serializable> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Serializable> extra) {
        this.extra = extra;
    }

    public Map<String, Serializable> addExtra(String key, Serializable value) {
        this.extra.put(key, value);
        return this.extra;
    }
}
