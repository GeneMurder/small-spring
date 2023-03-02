package com.yangweiyao.springframework.beans.factory;

import com.yangweiyao.springframework.beans.BeansException;
import com.yangweiyao.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.yangweiyao.springframework.beans.factory.config.BeanDefinition;
import com.yangweiyao.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * 提供分析和修改Bean以及预先实例化的操作接口
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}
