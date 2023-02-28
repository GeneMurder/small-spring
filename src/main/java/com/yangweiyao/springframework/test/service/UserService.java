package com.yangweiyao.springframework.test.service;

import com.yangweiyao.springframework.test.dao.UserDao;

public class UserService {

    private UserDao userDao;

    public void queryUserInfo(String userId) {
        System.out.println("查询用户信息: " + userDao.queryUserName(userId));
    }


}
