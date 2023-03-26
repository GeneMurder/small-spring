package com.yangweiyao.springframework.beans.factory;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-3-26 15:34
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
