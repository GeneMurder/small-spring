package com.yangweiyao.springframework.beans.factory.support;

import com.yangweiyao.springframework.beans.BeansException;
import com.yangweiyao.springframework.beans.factory.config.BeanDefinition;
import com.yangweiyao.springframework.beans.factory.config.BeanPostProcessor;
import com.yangweiyao.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.yangweiyao.springframework.utils.ClassUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    /** ClassLoader to resolve bean class names with, if necessary */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /** BeanPostProcessors to apply in createBean */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected Object doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition, args);
    }


    protected abstract Object createBean(String name, BeanDefinition beanDefinition, Object[] args);

    protected abstract Object createBean(String name, BeanDefinition beanDefinition);

    protected abstract BeanDefinition getBeanDefinition(String name);

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * Return the list of BeanPostProcessors that will get applied
     * to beans created with this factory.
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }
}
