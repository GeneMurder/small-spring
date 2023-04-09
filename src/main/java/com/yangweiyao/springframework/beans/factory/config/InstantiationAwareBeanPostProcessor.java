package com.yangweiyao.springframework.beans.factory.config;

import com.yangweiyao.springframework.beans.BeansException;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-4-8 22:37
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * Apply this BeanPostProcessor <i>before the target bean gets instantiated</i>.
     * The returned bean object may be a proxy to use instead of the target bean,
     * effectively suppressing default instantiation of the target bean.
     * 在 Bean 对象执行初始化方法之前，执行此方法
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

}
