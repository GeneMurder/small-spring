package com.yangweiyao.springframework.test;

import com.yangweiyao.springframework.beans.BeanReference;
import com.yangweiyao.springframework.beans.PropertyValue;
import com.yangweiyao.springframework.beans.PropertyValues;
import com.yangweiyao.springframework.beans.factory.config.BeanDefinition;
import com.yangweiyao.springframework.beans.factory.BeanFactory;
import com.yangweiyao.springframework.beans.factory.support.CglibSubclassingInstantiationStrategy;
import com.yangweiyao.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.yangweiyao.springframework.test.dao.UserDao;
import com.yangweiyao.springframework.test.service.UserService;

public class BeanFactoryTest {

    public static void main(String[] args) {
        // 1. 初始化Bean工厂
//        BeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanFactory beanFactory = new DefaultListableBeanFactory(new CglibSubclassingInstantiationStrategy());

        // 2. userDao注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. userService设置属性（注入userDao），并注册
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class, propertyValues));

        // 4. 获取bean，调用接口
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo("10002");

        // 5. 第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo("10001");

        System.out.println("Singleton: " + userService.equals(userService_singleton));
    }

}
