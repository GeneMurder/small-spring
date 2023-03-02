package com.yangweiyao.springframework.beans.factory.config;

/**
 * 属性依赖
 */
public class BeanReference {

    private String beanName;

    public BeanReference() {
    }

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
