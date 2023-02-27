package com.yangweiyao.springframework.test;

import com.yangweiyao.springframework.beans.factory.config.BeanDefinition;
import com.yangweiyao.springframework.beans.factory.BeanFactory;
import com.yangweiyao.springframework.beans.factory.support.CglibSubclassingInstantiationStrategy;
import com.yangweiyao.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.yangweiyao.springframework.test.service.UserService;

public class BeanFactoryTest {

    public static void main(String[] args) {
        // 1. 初始化Bean工厂
//        BeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanFactory beanFactory = new DefaultListableBeanFactory(new CglibSubclassingInstantiationStrategy());


        // 2. 注册bean
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class));

        // 3. 获取bean，调用接口
        UserService userService = (UserService) beanFactory.getBean("userService", "yangweiyao");
        userService.queryUserInfo();

        // 4. 第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();

        System.out.println("Singleton: " + userService.equals(userService_singleton));
    }

}
