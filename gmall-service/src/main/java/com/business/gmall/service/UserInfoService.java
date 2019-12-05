package com.business.gmall.service;

import com.business.gmall.bean.UserAddress;
import com.business.gmall.bean.UserInfo;

import java.util.List;

public interface UserInfoService {
    //写接口方法
    //实现查询
    List<UserInfo> getAllUser();

    //通过对象查询实现模糊查询
    List<UserInfo> getInfoByUser(UserInfo user);

    //通过对象某个字段查询
    List<UserInfo> getUserInfo(UserInfo user);

    //根据对象修改
    void updateUserInfo(UserInfo user);

    //根据主键修改
    void updateUserInfoByKey(UserInfo user);

    //根据对象删除字段
    void deleteUserInfo(UserInfo user);

    //获取用户所有的收货地址，使用对象
    List<UserAddress> getAddressByUser(UserAddress userAddress);

    //通过用户id,获取用户所有的收货地址
    List<UserAddress> getAddressByUser(String userId);
}
