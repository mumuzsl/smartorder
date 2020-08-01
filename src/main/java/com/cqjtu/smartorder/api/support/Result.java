package com.cqjtu.smartorder.api.support;


import org.springframework.http.HttpStatus;

/**
 * @author mumu
 * @date 2020/7/25
 */
public class Result<T> {
    private boolean success = true;
    private int code = HttpStatus.OK.value();
    private String message = "";
    private T data;

    public static <T> Result<T> build() {
        return new Result<T>();
    }

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(HttpStatus status) {
        this.message = status.getReasonPhrase();
        this.code = status.value();
    }

    public Result(String message) {
        this.message = message;
    }


    public Result(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public Result(int code, String message, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }


    public Result<T> status(HttpStatus status) {
        this.message = status.getReasonPhrase();
        this.code = status.value();
        return this;
    }

    public Result<T> ok() {
        success = true;
        return this;
    }

    public Result<T> fail() {
        success = false;
        return this;
    }

    public Result<T> message(String message) {
        this.message = message;
        return this;
    }

    public Result<T> data(T data) {
        this.data = data;
        return this;
    }


    public Result<T> code(int code) {
        this.code = code;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
}
