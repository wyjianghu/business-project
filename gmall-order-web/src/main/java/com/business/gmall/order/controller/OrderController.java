package com.business.gmall.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.business.gmall.bean.UserAddress;
import com.business.gmall.bean.UserInfo;
import com.business.gmall.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

//    @Autowired
    @Reference
    private UserInfoService userInfoService;

    @RequestMapping("getUserOrder")
    public List<UserAddress> getOrder(String userId){
        return userInfoService.getAddressByUser(userId);
    }

}
