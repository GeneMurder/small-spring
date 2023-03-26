package com.yangweiyao.springframework.test.service;

import com.yangweiyao.springframework.beans.BeansException;
import com.yangweiyao.springframework.beans.factory.*;
import com.yangweiyao.springframework.context.ApplicationContext;
import com.yangweiyao.springframework.context.ApplicationContextAware;
import com.yangweiyao.springframework.test.dao.UserDao;

public class UserService implements DisposableBean, BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {

    private static int count = 0;
    private UserDao userDao;
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    public void queryUserInfo(String userId) {
        count ++;
        System.out.printf("查询用户信息: %s, count: %d%n", userDao.queryUserName(userId), count);
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        UserService.count = count;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("模拟UserService释放内存");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader：" + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name is：" + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
