package com.yangweiyao.springframework.test.processor;

import com.yangweiyao.springframework.beans.BeansException;
import com.yangweiyao.springframework.beans.PropertyValue;
import com.yangweiyao.springframework.beans.PropertyValues;
import com.yangweiyao.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.yangweiyao.springframework.beans.factory.config.BeanDefinition;
import com.yangweiyao.springframework.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("count", "10"));
    }
}
