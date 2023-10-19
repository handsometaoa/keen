package com.simple.keen.common.base.dto;


import java.io.Serializable;

public class Request<T extends Serializable> implements Serializable {
    protected T data;

    public Request() {
    }

    public Request(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
