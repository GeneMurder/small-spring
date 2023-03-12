package com.yangweiyao.springframework.test.service;

import com.yangweiyao.springframework.beans.factory.DisposableBean;
import com.yangweiyao.springframework.test.dao.UserDao;

public class UserService implements DisposableBean {

    private static int count = 0;
    private UserDao userDao;

    public void queryUserInfo(String userId) {
        count ++;
        System.out.printf("查询用户信息: %s, count: %d%n", userDao.queryUserName(userId), count);
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        UserService.count = count;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("模拟UserService释放内存");
    }
}
