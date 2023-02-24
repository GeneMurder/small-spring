package com.yangweiyao.springframework.beans.factory.config;

/**
 * 用于定义 Bean 实例化信息
 */
public class BeanDefinition {

    private Class<?> beanClass;

    public BeanDefinition() {
    }

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBean() {
        return beanClass;
    }


}
