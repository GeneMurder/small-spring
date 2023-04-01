package com.yangweiyao.springframework.test;

import com.yangweiyao.springframework.aop.AdvisedSupport;
import com.yangweiyao.springframework.aop.TargetSource;
import com.yangweiyao.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.yangweiyao.springframework.aop.framework.Cglib2AopProxy;
import com.yangweiyao.springframework.aop.framework.JdkDynamicAopProxy;
import com.yangweiyao.springframework.test.interceptor.UserServiceInterceptor;
import com.yangweiyao.springframework.test.service.IUserService;
import com.yangweiyao.springframework.test.service.impl.IUserServiceImpl;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-4-1 15:29
 */
public class AopProxyTest {

    public static void main(String[] args) {
        // 目标对象
        IUserService userService = new IUserServiceImpl();

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.yangweiyao.springframework.test.service.IUserService.*(..))"));

        // 代理对象(JdkDynamicAopProxy)
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();

        // 测试调用
        System.out.println("测试结果：" + proxy_jdk.queryUserName());

        // 代理对象(Cglib2AopProxy)
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_cglib.queryUserName());

    }

}
