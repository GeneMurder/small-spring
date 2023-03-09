package com.yangweiyao.springframework.beans.factory.support;

import com.yangweiyao.springframework.beans.factory.DisposableBean;
import com.yangweiyao.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @Author: yangweiyao
 * @CreateTime: 2023-03-08 17:36
 **/
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private final String destroyMethodName;


    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 1. 实现接口 DisposableBean
        if(bean instanceof DisposableBean) {
            DisposableBean disposableBean = (DisposableBean) bean;
            disposableBean.destroy();
        }

        // 2. 配置信息 destroy-method {判断是为了避免二次执行销毁}
        if(destroyMethodName != null && destroyMethodName.length() != 0
                && !(bean instanceof DisposableBean && "destroy".equals(destroyMethodName))) {
            Method method = bean.getClass().getMethod(destroyMethodName);
            method.invoke(bean);
        }

    }

}
