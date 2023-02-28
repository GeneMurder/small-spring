package com.yangweiyao.springframework.test.dao;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "张三");
        hashMap.put("10002", "李四");
        hashMap.put("10003", "王五");
    }

    /**
     * 模拟查询数据库
     * @param userId 用户ID
     * @return 用户名称
     */
    public String queryUserName(String userId) {
        System.out.println("查询数据库, userId:" + userId);
        return hashMap.get(userId);
    }

}
