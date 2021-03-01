package com.bespin.wzu3.api.v1.controller;

import com.bespin.wzu3.config.resp.ResponseResult;
import com.bespin.wzu3.entity.TableEntity;
import com.bespin.wzu3.mapper.TableMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author voidm
 * @date 2021/2/19
 */

@RestController()
@RequestMapping("/api")
public class MyBatiesController {

    @Autowired
    private TableMapper tableMapper;

    @GetMapping("/user/add")
    public ResponseResult userAdd (){
        TableEntity tableEntity = new TableEntity();
        tableEntity.setName(System.currentTimeMillis()+"");
        tableMapper.randomInsert(tableEntity);
        return ResponseResult.success(tableEntity.getId());
    }

    @GetMapping("/user")
    public ResponseResult user (){
        return ResponseResult.success(tableMapper.findOneById(1));
    }

    @GetMapping("/users")
    public ResponseResult users (){
        TableEntity tableEntity = new TableEntity();
        return ResponseResult.success(tableMapper.findListByEntity(tableEntity));
    }
    @GetMapping("/users2")
    public ResponseResult users2 (){
        return this.user();
    }
    @GetMapping("/page/user")
    public ResponseResult pageUser () {
        //使用PageHelper设置分页，为了安全分页，后边最好紧跟mybatis mapper方法
        //注意这里看起来似乎是属于内存分页，但其实PageHelper插件对mybatis执行流程进行了增强，属于物理分页
        PageHelper.startPage(1,5);
        List<TableEntity> users = tableMapper.selectAll();
        //返回的是一个PageInfo,包含了分页的所有信息
        PageInfo<TableEntity> pageInfo = new PageInfo<>(users);
        return ResponseResult.success(pageInfo);
    }

}