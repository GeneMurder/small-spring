package com.yangweiyao.springframework.aop.framework.autoproxy;
import com.yangweiyao.springframework.aop.framework.ProxyFactory;
import org.aopalliance.intercept.MethodInterceptor;
import com.yangweiyao.springframework.aop.MethodMatcher;
import com.yangweiyao.springframework.aop.TargetSource;

import com.yangweiyao.springframework.aop.*;
import com.yangweiyao.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.yangweiyao.springframework.beans.BeansException;
import com.yangweiyao.springframework.beans.factory.BeanFactory;
import com.yangweiyao.springframework.beans.factory.BeanFactoryAware;
import com.yangweiyao.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.yangweiyao.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;

import java.util.Collection;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-4-8 22:36
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(isInfrastructureClass(beanClass)) return null;

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {

            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if(!classFilter.matches(beanClass)) continue;

            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }

            AdvisedSupport advisedSupport = new AdvisedSupport();
            advisedSupport.setProxyTargetClass(false);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            return new ProxyFactory(advisedSupport).getProxy();
        }
        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass)
                || Pointcut.class.isAssignableFrom(beanClass)
                || Advisor.class.isAssignableFrom(beanClass);
    }

}
