package com.yangweiyao.springframework.beans.factory;


import com.yangweiyao.springframework.beans.BeansException;

public interface BeanFactory {

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    /**
     * 获取bean实例
     * @param name bean名称
     * @return bean实例
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

    /**
     * 获取bean实例
     * @param name bean名称
     * @param args 入参
     * @return bean实例
     * @throws BeansException
     */
    Object getBean(String name, Object... args) throws BeansException;

}
