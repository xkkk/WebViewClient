package com.zwj.agentweb.http;

public class Response<T> {
    /**
     * 返回码
     */
    private String code;
    /**
     * 返回的数据
     */
    private T data;
    /**
     * 返回的信息
     */
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
