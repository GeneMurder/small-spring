package com.yangweiyao.springframework.beans.factory;

import com.yangweiyao.springframework.beans.BeansException;
import com.yangweiyao.springframework.context.ApplicationContext;

/**
 * 实现此接口，既能感知到所属的 ApplicationContext
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String name);

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
