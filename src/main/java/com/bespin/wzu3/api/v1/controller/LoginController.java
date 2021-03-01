package com.bespin.wzu3.api.v1.controller;

import com.alibaba.fastjson.JSONObject;
import com.bespin.wzu3.config.security.JwtTokenUtil;
import com.bespin.wzu3.config.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author voidm
 * @date 2021/2/19
 */

@RestController()
@RequestMapping("/am")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/login")
    public JSONObject login (@RequestParam(name = "username") String username, @RequestParam(name = "password") String password){
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken( username, password );
        // 该方法会去调用 userDetailsService.loadUserByUsername()去验证用户名和密码，如果正确，则存储该用户名密码到“security 的 context中”
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        JwtUser principal = (JwtUser) authentication.getPrincipal();
        String token = jwtTokenUtil.generateToken(principal);
        JSONObject result = new JSONObject();
        result.put("Authorization", token);
        return result;
    }
}