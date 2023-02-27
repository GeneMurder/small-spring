package com.yangweiyao.springframework.test.service;

public class UserService {

    private String username;

    public UserService(String username) {
        this.username = username;
    }

    public void queryUserInfo() {
        System.out.println("模拟查询用户信息: " + username);
    }


}
