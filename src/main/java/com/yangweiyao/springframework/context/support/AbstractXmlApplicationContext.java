package com.yangweiyao.springframework.context.support;

import com.yangweiyao.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.yangweiyao.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Objects;

/**
 * 上下文中对配置信息的加载
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    /**
     * 使用 XmlBeanDefinitionReader 类，处理了关于 XML 文件配置信息的操作。
     * @param beanFactory
     */
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if(Objects.nonNull(configLocations)) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 此方法是为了从入口上下文类，拿到配置信息的地址描述。
     * @return
     */
    protected abstract String[] getConfigLocations();
}
