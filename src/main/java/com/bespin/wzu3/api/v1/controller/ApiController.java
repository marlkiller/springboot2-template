package com.bespin.wzu3.api.v1.controller;

import com.alibaba.fastjson.JSONObject;
import com.bespin.wzu3.config.resp.ResponseResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author voidm
 * @date 2021/2/19
 */

@RestController()
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello")
    public ResponseResult hello (){
        JSONObject result = new JSONObject();
        result.put("time", new Date());
        result.put("principal", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseResult.success(result);
    }

}