package com.yangweiyao.springframework.beans.factory.support;

import com.yangweiyao.springframework.beans.BeansException;
import com.yangweiyao.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.util.Objects;

public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName,
                              Constructor<?> constructor, Object[] args) throws BeansException {
        Class<?> clazz = beanDefinition.getBean();
        try {
            return Objects.nonNull(constructor) ?
                    clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args)
                    : clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }

}
