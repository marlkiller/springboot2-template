package com.bespin.wzu3.api.v1.controller;

import com.bespin.wzu3.config.exception.I18nException;
import com.bespin.wzu3.config.resp.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author voidm
 * @date 2021/2/19
 */

@RestController()
@RequestMapping("/api")
public class ErrorController {

    @GetMapping("/ex1")
    public ResponseResult ex1 () throws I18nException {
        throw new I18nException("0001","I18nException : this is ex msg");
    }

    @GetMapping("/ex2")
    public ResponseResult ex2 () throws Exception {
        throw new I18nException("0002","I18nException : this is ex msg");
    }

    @GetMapping("/ex3")
    public ResponseResult ex3 () throws Exception {
        throw new I18nException("0003","I18nException : this is ex msg");
    }

    @GetMapping("/ex4")
    public ResponseResult ex4 () throws Exception {
        throw new Exception("0004");
    }
}