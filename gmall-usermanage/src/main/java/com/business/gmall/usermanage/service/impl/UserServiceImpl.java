package com.business.gmall.usermanage.service.impl;

import com.business.gmall.bean.UserAddress;
import com.business.gmall.bean.UserInfo;
import com.business.gmall.service.UserInfoService;
import com.business.gmall.usermanage.mapper.UserAddressMapper;
import com.business.gmall.usermanage.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


//@Service
@com.alibaba.dubbo.config.annotation.Service //使用阿里的service
public class UserServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserAddressMapper userAddressMapper;

   //查询所有的用户
    @Override
    public List<UserInfo> getAllUser() {
        return userInfoMapper.selectAll();
    }

    @Override
    public List<UserInfo> getInfoByUser(UserInfo user) {
        return null;
    }

    @Override
    public List<UserInfo> getUserInfo(UserInfo user) {
        return null;
    }

    @Override
    public void updateUserInfo(UserInfo user) {

    }

    @Override
    public void updateUserInfoByKey(UserInfo user) {

    }

    @Override
    public void deleteUserInfo(UserInfo user) {

    }

    //获取所有的用户订单信息
    @Override
    public List<UserAddress> getAddressByUser(UserAddress userAddress) {
        return userAddressMapper.select(userAddress);
    }

    //通过用户id获取用户的信息
    @Override
    public List<UserAddress> getAddressByUser(String userId) {
        // select * from UserAddress where userId = ?
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        //查询
        List<UserAddress> addressUser = userAddressMapper.select(userAddress);
        return addressUser;
    }
}
