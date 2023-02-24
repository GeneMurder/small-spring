package com.yangweiyao.springframework.beans.factory;


import com.yangweiyao.springframework.beans.BeansException;
import com.yangweiyao.springframework.beans.factory.config.BeanDefinition;

public interface BeanFactory {

    /**
     * 获取bean实例
     * @param name bean名称
     * @return bean实例
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

    /**
     * 注册Bean实例
     * @param name bean名称
     * @param beanDefinition
     */
    void registerBeanDefinition(String name, BeanDefinition beanDefinition);

}
