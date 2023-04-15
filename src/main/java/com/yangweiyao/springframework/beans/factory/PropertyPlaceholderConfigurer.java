package com.yangweiyao.springframework.beans.factory;

import com.yangweiyao.springframework.beans.BeansException;
import com.yangweiyao.springframework.beans.PropertyValue;
import com.yangweiyao.springframework.beans.PropertyValues;
import com.yangweiyao.springframework.beans.factory.config.BeanDefinition;
import com.yangweiyao.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.yangweiyao.springframework.core.io.DefaultResourceLoader;
import com.yangweiyao.springframework.core.io.Resource;
import com.yangweiyao.springframework.core.io.ResourceLoader;

import java.util.Properties;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-4-15 14:56
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    /**
     * Default placeholder prefix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * Default placeholder suffix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanDefinitionName : beanDefinitionNames) {

                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();

                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) continue;
                    String strVal = ((String) value);

                    StringBuilder builder = new StringBuilder();
                    int startIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int stopIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if(startIndex != -1 && stopIndex != -1 && startIndex < stopIndex) {
                        String propKey = strVal.substring(startIndex + 2, stopIndex);
                        String propVal = properties.getProperty(propKey);
                        builder.replace(startIndex, stopIndex + 1, propVal);
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), builder.toString()));
                    }

                }
            }
        } catch (Exception e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
