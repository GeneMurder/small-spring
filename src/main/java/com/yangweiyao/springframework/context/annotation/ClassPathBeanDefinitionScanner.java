package com.yangweiyao.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.yangweiyao.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.yangweiyao.springframework.beans.factory.config.BeanDefinition;
import com.yangweiyao.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.yangweiyao.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

/**
 * @author YangWeiYao
 * @Description 在 doScan 中除了获取到扫描的类信息以后，还需要获取 Bean 的作用域和类名，如果不配置类名基本都是把首字母缩写。
 * @date 2023-4-15 15:20
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private final BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidateComponents = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidateComponents) {
                // 解析 Bean 的作用域 singleton、prototype
                String beanScope = resolveBeanScope(beanDefinition);
                if(StrUtil.isNotEmpty(beanScope)) {
                    beanDefinition.setScope(beanScope);
                }
                registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }

        // 注册处理注解的 BeanPostProcessor（@Autowired、@Value）
        registry.registerBeanDefinition("com.yangweiyao.springframework.context.annotation.internalAutowiredAnnotationProcessor", new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }

    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBean();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        return StrUtil.isEmpty(value) ? StrUtil.lowerFirst(beanClass.getSimpleName()) : value;
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBean();
        Scope scope = beanClass.getAnnotation(Scope.class);
        return Objects.nonNull(scope) ? scope.value() : StrUtil.EMPTY;
    }

    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }
}
