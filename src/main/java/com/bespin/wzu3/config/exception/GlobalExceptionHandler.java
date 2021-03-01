package com.bespin.wzu3.config.exception;

import com.bespin.wzu3.constants.APIConstant;
import com.bespin.wzu3.config.resp.ResponseCode;
import com.bespin.wzu3.config.resp.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局国际化异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @Autowired
    private I18nExceptionUtils i18nExceptionUtils;

    @ExceptionHandler(value = Exception.class)
    public ResponseResult<Object> exceptionHandler (Exception exception, HttpServletRequest request) {
        if (exception instanceof I18nException) {
            // 自定义异常处理
            I18nException e = (I18nException) exception;
            String message = i18nExceptionUtils.getLocaleMessage(request, e.getCode(), APIConstant.Msg.DEFAULT_SERVER_EXCEPTION, e.getParams());
            log.error("error code:{}, error message:{}", e.getCode(), message);
            return ResponseResult.build(e.getCode(), message);
        } else {
            // 通用异常处理
            log.error("un known exception", exception);
            return ResponseResult.build(ResponseCode.ERROR, exception.getMessage());
        }
    }
}