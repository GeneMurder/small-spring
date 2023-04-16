package com.yangweiyao.springframework.beans.factory;

import com.yangweiyao.springframework.beans.BeansException;
import com.yangweiyao.springframework.beans.PropertyValue;
import com.yangweiyao.springframework.beans.PropertyValues;
import com.yangweiyao.springframework.beans.factory.config.BeanDefinition;
import com.yangweiyao.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.yangweiyao.springframework.core.io.DefaultResourceLoader;
import com.yangweiyao.springframework.core.io.Resource;
import com.yangweiyao.springframework.core.io.ResourceLoader;
import com.yangweiyao.springframework.utils.StringValueResolver;

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

            // 加载属性文件
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);

            // 占位符替换属性值
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanDefinitionName : beanDefinitionNames) {

                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();

                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) continue;
                    value = resolvePlaceholder((String) value, properties);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), value));

                }

                // 向容器中添加字符串解析器，供解析@Value注解使用
                StringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
                beanFactory.addEmbeddedValueResolver(valueResolver);
            }
        } catch (Exception e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    private String resolvePlaceholder(String strVal, Properties properties) {
        StringBuilder builder = new StringBuilder();
        int startIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if(startIndex != -1 && stopIndex != -1 && startIndex < stopIndex) {
            String propKey = strVal.substring(startIndex + 2, stopIndex);
            String propVal = properties.getProperty(propKey);
            builder.replace(startIndex, stopIndex + 1, propVal);
        }
        return builder.toString();
    }

    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {

        private final Properties properties;

        public PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolveStringValue(String strVal) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal, properties);
        }

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
