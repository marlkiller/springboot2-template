package com.bespin.wzu3.config.resp;

public enum ResponseCode implements CodeDefinition {
    SUCCESS("200", "成功"),
    AUTHEN("401", "登录认证失败"),
    ACCESS("403", "没有权限"),
    ERROR("500", "服务器内部未知错误"),
    ERROR_VALID("00001001", "参数验证错误");

    private String code;
    private String message;

    private ResponseCode (String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}