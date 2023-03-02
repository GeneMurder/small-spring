package com.yangweiyao.springframework.beans.factory.support;

import com.yangweiyao.springframework.beans.BeansException;
import com.yangweiyao.springframework.core.io.Resource;
import com.yangweiyao.springframework.core.io.ResourceLoader;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

}
