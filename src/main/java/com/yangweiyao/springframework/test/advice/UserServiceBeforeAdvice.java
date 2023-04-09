package com.yangweiyao.springframework.test.advice;

import com.yangweiyao.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-4-8 23:12
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法：" + method.getName());
    }

}
