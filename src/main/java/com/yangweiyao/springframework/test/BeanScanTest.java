package com.yangweiyao.springframework.test;

import com.yangweiyao.springframework.context.support.ClassPathXmlApplicationContext;
import com.yangweiyao.springframework.test.service.impl.LoginServiceImpl;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-4-15 16:05
 */
public class BeanScanTest {

    public static void main(String[] args) {
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-property.xml");
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        LoginServiceImpl loginService = applicationContext.getBean("loginServiceImpl", LoginServiceImpl.class);
        System.out.println(loginService.login("root"));
    }
}
