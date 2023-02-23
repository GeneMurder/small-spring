package com.yangweiyao.springframework.test;

import com.yangweiyao.springframework.BeanDefinition;
import com.yangweiyao.springframework.BeanFactory;
import com.yangweiyao.springframework.test.service.UserService;

public class BeanFactoryTest {

    public static void main(String[] args) {
        // 1. 初始化Bean工厂
        BeanFactory beanFactory = new BeanFactory();

        // 2. 注册bean
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(new UserService()));

        // 3. 获取bean，调用接口
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

}
