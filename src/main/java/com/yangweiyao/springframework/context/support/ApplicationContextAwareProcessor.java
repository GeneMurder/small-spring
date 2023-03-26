package com.yangweiyao.springframework.context.support;

import com.yangweiyao.springframework.beans.BeansException;
import com.yangweiyao.springframework.beans.factory.config.BeanPostProcessor;
import com.yangweiyao.springframework.context.ApplicationContext;
import com.yangweiyao.springframework.context.ApplicationContextAware;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-3-26 14:16
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
