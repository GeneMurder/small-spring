package com.yangweiyao.springframework.test.dao;

import com.yangweiyao.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;

public class UserDao implements InitializingBean {

    private String version;
    private static Map<String, String> hashMap;

//    static {
//        hashMap.put("10001", "张三");
//        hashMap.put("10002", "李四");
//        hashMap.put("10003", "王五");
//    }
    public void init() {
        System.out.println("UserDao-加载数据中...");
        hashMap.put("10001", "张三");
        hashMap.put("10002", "李四");
        hashMap.put("10003", "王五");
    }

    public void close() {
        System.out.println("UserDao-关闭连接...");
        hashMap.clear();
    }


    /**
     * 模拟查询数据库
     * @param userId 用户ID
     * @return 用户名称
     */
    public String queryUserName(String userId) {
        System.out.printf("查询数据库, version:%s, userId:%s%n", version, userId);
        return hashMap.get(userId);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UserDao-初始化缓存容量");
        hashMap = new HashMap<>(4);
    }
}
