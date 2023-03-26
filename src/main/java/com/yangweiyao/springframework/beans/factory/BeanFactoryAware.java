package com.yangweiyao.springframework.beans.factory;

import com.yangweiyao.springframework.beans.BeansException;

/**
 * @author YangWeiYao
 * @Description 实现此接口，既能感知到所属的 BeanFactory
 * @date 2023-3-26 14:12
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
