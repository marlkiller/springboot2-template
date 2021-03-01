// package com.bespin.wzu3.api.v1.controller;
//
// import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
// import com.bespin.wzu3.config.resp.ResponseResult;
// import com.bespin.wzu3.entity.ImageEntity;
// import com.bespin.wzu3.mapper.ImageMapper;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// /**
//  * @author voidm
//  * @date 2021/2/19
//  */
//
// @RestController()
// @RequestMapping("/api")
// public class MyBatiesPlusController {
//
//     @Autowired
//     private ImageMapper imageMapper;
//
//     @GetMapping("/user/plus")
//     public ResponseResult userPlus () {
//         return ResponseResult.success(imageMapper.selectList(new QueryWrapper<ImageEntity>()));
//     }
// }