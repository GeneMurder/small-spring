package com.yangweiyao.springframework.beans.factory.support;

import com.yangweiyao.springframework.beans.BeansException;
import com.yangweiyao.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.util.Objects;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 一个是基于 Java 本身自带的方法 DeclaredConstructor，
     * 另外一个是使用 Cglib 来动态创建 Bean 对象。
     * Cglib 是基于字节码框架 ASM 实现，所以也可以直接通过 ASM 操作指令码来创建对象
     */
    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition) {
        try {
            Object bean = beanDefinition.getBean().newInstance();
            addSingleton(name, bean);
            return bean;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
    }

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition, Object[] args) {
        try {
            Object bean = createBeanInstance(name, beanDefinition, args);
            addSingleton(name, bean);
            return bean;
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
    }

    protected Object createBeanInstance(String name, BeanDefinition beanDefinition, Object[] args) {
        Constructor<?> constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBean();
        // 获取到所有的构造函数
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for(Constructor<?> constructor : declaredConstructors) {
            // 比对出构造函数集合与入参信息 args 的匹配情况
            // 实际 Spring 源码中还需要比对入参类型
            if(Objects.nonNull(args) && constructor.getParameterTypes().length == args.length) {
                constructorToUse = constructor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, name, constructorToUse, args);
    }

    /**
     * 获取对象实例化策略
     * @return InstantiationStrategy
     */
    protected abstract InstantiationStrategy getInstantiationStrategy();

}
