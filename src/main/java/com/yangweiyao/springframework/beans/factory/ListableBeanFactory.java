package com.yangweiyao.springframework.beans.factory;

import com.yangweiyao.springframework.beans.BeansException;

import java.util.Map;

/**
 * 扩展 Bean 工厂接口的接口
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回 Bean 实例
     * @param type 类型
     * @param <T> class类型
     * @return Bean 实例
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * Return the names of all beans defined in this registry.
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();

}
