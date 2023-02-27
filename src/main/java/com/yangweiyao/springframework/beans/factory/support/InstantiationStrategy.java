package com.yangweiyao.springframework.beans.factory.support;

import com.yangweiyao.springframework.beans.BeansException;
import com.yangweiyao.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {

    /**
     *
     * @param beanDefinition 定义信息
     * @param beanName bean 名称
     * @param constructor 可以拿到符合入参信息相对应的构造函数
     * @param args 具体的入参信息了，最终实例化时候会用到。
     * @return 具体实例对象
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName,
                       Constructor<?> constructor, Object[] args) throws BeansException;

}
