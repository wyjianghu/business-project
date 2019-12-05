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

    //通过用户id获取用户发货地址
    @RequestMapping("getUserAddressByUserId")
    public List<UserAddress> getOrder(String userId){
        return userInfoService.getAddressByUser(userId);
    }

    //传入用户地址对象，获取地址信息
    @RequestMapping("getUserAddress") //和上边方法为方法重载
    public List<UserAddress> getUserAddress(UserAddress userAddress){
        return userInfoService.getAddressByUser(userAddress);
    }
}
