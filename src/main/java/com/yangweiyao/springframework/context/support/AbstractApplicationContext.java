package com.yangweiyao.springframework.context.support;

import com.yangweiyao.springframework.beans.BeansException;
import com.yangweiyao.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.yangweiyao.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.yangweiyao.springframework.beans.factory.config.BeanPostProcessor;
import com.yangweiyao.springframework.context.ConfigurableApplicationContext;
import com.yangweiyao.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * AbstractApplicationContext 继承 DefaultResourceLoader 是为了处理 spring.xml 配置资源的加载。
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader
        implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {

        // 1. 创建BeanFactory, 并加载BeanDefinition
        refreshBeanFactory();

        // 2. 获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. 添加 ApplicationContextAwareProcessor，让继承自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 4. 在Bean实例化之前，执行BeanFactoryPostProcessor
        // (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);

        // 5. BeanPostProcessor需要提前于其他Bean对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 6. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }

    }

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap  = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor  : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    protected abstract void refreshBeanFactory();

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }
}
