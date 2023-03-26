package com.yangweiyao.springframework.test;

import com.yangweiyao.springframework.context.support.ClassPathXmlApplicationContext;
import com.yangweiyao.springframework.test.service.UserService;

public class ApplicationContextTest {

    public static void main(String[] args) {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 4. 获取bean，调用接口
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.queryUserInfo("10002");
        System.out.println("ApplicationContextAware：" + userService.getApplicationContext());
        System.out.println("BeanFactoryAware：" + userService.getBeanFactory());

        // 5. 第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) applicationContext.getBean("userService");
        userService_singleton.queryUserInfo("10001");

        System.out.println("Singleton: " + userService.equals(userService_singleton));
    }

}
