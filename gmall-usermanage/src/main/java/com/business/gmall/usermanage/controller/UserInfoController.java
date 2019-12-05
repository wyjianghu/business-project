package com.business.gmall.usermanage.controller;

import com.business.gmall.bean.UserInfo;
import com.business.gmall.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("getAllUser")
    public List<UserInfo> getAllUser(){
        return userInfoService.getAllUser();
    }
}
