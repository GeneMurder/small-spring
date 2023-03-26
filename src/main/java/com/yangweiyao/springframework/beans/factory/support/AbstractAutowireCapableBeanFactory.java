package com.yangweiyao.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.sun.xml.internal.ws.util.StringUtils;
import com.yangweiyao.springframework.beans.factory.*;
import com.yangweiyao.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.yangweiyao.springframework.beans.factory.config.BeanPostProcessor;
import com.yangweiyao.springframework.beans.factory.config.BeanReference;
import com.yangweiyao.springframework.beans.BeansException;
import com.yangweiyao.springframework.beans.PropertyValue;
import com.yangweiyao.springframework.beans.PropertyValues;
import com.yangweiyao.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

public abstract class AbstractAutowireCapableBeanFactory extends
        AbstractBeanFactory implements AutowireCapableBeanFactory {

    /**
     * 一个是基于 Java 本身自带的方法 DeclaredConstructor，
     * 另外一个是使用 Cglib 来动态创建 Bean 对象。
     * Cglib 是基于字节码框架 ASM 实现，所以也可以直接通过 ASM 操作指令码来创建对象
     */
    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition) {
        return createBean(name, beanDefinition, null);
    }

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition, Object[] args) {
        Object bean;
        try {
            if(args == null || args.length == 0) {
                bean = beanDefinition.getBean().newInstance();
            } else {
                bean = createBeanInstance(name, beanDefinition, args);
            }
            // 给 Bean 填充属性
            applyPropertyValues(name, bean, beanDefinition);
            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(name, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 注册实现了 DisposableBean 接口的 Bean 对象
        registerDisposableBeanIfNecessary(name, bean, beanDefinition);

        addSingleton(name, bean);
        return bean;
    }

    private void registerDisposableBeanIfNecessary(String name, Object bean, BeanDefinition beanDefinition) {
        if(bean instanceof DisposableBean || (beanDefinition.getDestroyMethodName() != null
                && beanDefinition.getDestroyMethodName().length() != 0)) {
            registerDisposableBean(name, new DisposableBeanAdapter(bean, name, beanDefinition));
        }
    }

    private Object initializeBean(String name, Object bean, BeanDefinition beanDefinition) {
        // invokeAwareMethods
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if (bean instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(name);
            }
        }

        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, name);
        // 执行 Bean 对象的初始化方法
        try {
            invokeInitMethods(name, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + name + "] failed", e);
        }
        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, name);
        return wrappedBean;
    }


    private void invokeInitMethods(String name, Object bean, BeanDefinition beanDefinition) throws Exception {
        // 1. 实现接口 InitializingBean
        if(bean instanceof InitializingBean) {
            InitializingBean initializingBean = (InitializingBean) bean;
            initializingBean.afterPropertiesSet();
        }

        // 2. 配置信息 init-method {判断是为了避免二次执行销毁}
        String initMethodName = beanDefinition.getInitMethodName();
        if(Objects.nonNull(initMethodName) && initMethodName.length() != 0) {
            Method method = beanDefinition.getBean().getMethod(initMethodName);
            method.invoke(bean);
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            Object o = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if(Objects.isNull(o)) return result;
            result = o;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            Object o = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            if(Objects.isNull(o)) return result;
            result = o;
        }
        return result;
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            if(value instanceof BeanReference) {
                // A依赖B, 获取B的实例化
                // TODO 需要注意我们并没有去处理循环依赖的问题
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getBeanName());
            }
            // 属性填充
            BeanUtil.setFieldValue(bean, name, value);
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
