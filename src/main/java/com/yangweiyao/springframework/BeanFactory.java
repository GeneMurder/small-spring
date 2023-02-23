package com.yangweiyao.springframework;

import java.util.HashMap;
import java.util.Map;

/**
 * bean工厂
 */
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * 获取Bean
     * @param name bean名称
     * @return Bean
     */
    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    /**
     * 注册Bean的定义信息
     * @param name bean名称
     * @param beanDefinition Bean的定义信息
     */
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

}
