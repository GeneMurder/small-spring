package com.yangweiyao.springframework.test;

import com.yangweiyao.springframework.context.support.ClassPathXmlApplicationContext;
import com.yangweiyao.springframework.test.service.IUserService;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-4-8 23:20
 */
public class SpringAopTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("iUserService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserName());
    }

}
