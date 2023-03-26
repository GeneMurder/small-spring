package com.yangweiyao.springframework.test.proxy;

import com.yangweiyao.springframework.beans.factory.FactoryBean;
import com.yangweiyao.springframework.test.dao.IUserDao;
import com.yangweiyao.springframework.test.dao.UserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YangWeiYao
 * @Description 这是一个实现接口 FactoryBean 的代理类 ProxyBeanFactory 名称，
 * 主要是模拟了 UserDao 的原有功能，类似于 MyBatis 框架中的代理操作。
 * @date 2023-3-26 16:09
 */
public class ProxyBeanFactory implements FactoryBean<IUserDao> {

    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {
            Map<String, String> hashMap = new HashMap<>(4);
            hashMap.put("10001", "张三");
            hashMap.put("10002", "李四");
            hashMap.put("10003", "王五");
            return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{IUserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
