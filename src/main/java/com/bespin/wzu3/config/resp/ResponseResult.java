package com.bespin.wzu3.config.resp;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 19695416399404825L;
    private String code;
    private String message;
    private T result;

    public ResponseResult() {
    }

    public ResponseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(String code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public ResponseResult(CodeDefinition code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public ResponseResult(CodeDefinition code, String msg) {
        this.code = code.getCode();
        this.message = msg;
    }

    public ResponseResult(CodeDefinition code, String msg, T result) {
        this.code = code.getCode();
        this.message = msg;
        this.result = result;
    }

    public ResponseResult(CodeDefinition code, T result) {
        this(code);
        this.result = result;
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult(ResponseCode.SUCCESS, new Object());
    }

    public static <T> ResponseResult<T> success(T result) {
        return null == result ? new ResponseResult(ResponseCode.SUCCESS, new Object()) : new ResponseResult(ResponseCode.SUCCESS, result);
    }

    public static <T> ResponseResult<T> success(String message) {
        return new ResponseResult(ResponseCode.SUCCESS, message, new Object());
    }

    public static <T> ResponseResult<T> error() {
        return new ResponseResult(ResponseCode.ERROR);
    }

    public static <T> ResponseResult<T> error(String message) {
        return new ResponseResult(ResponseCode.ERROR, message);
    }

    public static <T> ResponseResult<T> build(CodeDefinition code) {
        return new ResponseResult(code);
    }

    public static <T> ResponseResult<T> build(CodeDefinition code, T result) {
        return new ResponseResult(code, result);
    }

    public static <T> ResponseResult<T> build(String code, String message) {
        return new ResponseResult(code, message);
    }

    public static <T> ResponseResult<T> build(CodeDefinition code, String error) {
        return new ResponseResult(code, error);
    }

    public ResponseResult<T> withResult(T value) {
        this.setResult(value);
        return this;
    }

    public String toString() {
        return "ResponseResult(code=" + this.getCode() + ", message=" + this.getMessage() + ", result=" + this.getResult() + ")";
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setResult(final T result) {
        this.result = result;
    }

    public T getResult() {
        return this.result;
    }
}