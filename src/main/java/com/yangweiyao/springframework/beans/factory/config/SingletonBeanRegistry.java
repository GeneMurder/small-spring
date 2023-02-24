package com.yangweiyao.springframework.beans.factory.config;

/**
 * 定义了一个获取单例对象的接口
 */
public interface SingletonBeanRegistry {

    /**
     * 获取单例Bean
     * @param beanName bean名称
     * @return 单例Bean
     */
    Object getSingleton(String beanName);

}
