package com.bespin.wzu3.config.exception;

/**
 * @author voidm
 * @date 2020/11/30
 */
public class I18nException extends Exception {
    private String code;
    private Object[] params;


    public Object[] getParams () {
        return params;
    }

    public String getCode () {
        return code;
    }

    public I18nException (String code, Object... params) {
        super(code);
        this.code = code;
        this.params = params;
    }
}