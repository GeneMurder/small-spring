package com.yangweiyao.springframework.test;

import com.yangweiyao.springframework.context.support.ClassPathXmlApplicationContext;
import com.yangweiyao.springframework.test.event.CustomEvent;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-3-26 18:16
 */
public class CustomEventTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));
        applicationContext.registerShutdownHook();
    }

}
