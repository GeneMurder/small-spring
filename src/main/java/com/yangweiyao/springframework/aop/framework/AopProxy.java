package com.yangweiyao.springframework.aop.framework;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-4-1 14:54
 */
public interface AopProxy {

    /**
     * 用于获取代理类。因为具体实现代理的方式可以有 JDK 方式，也可以是 Cglib 方式
     * @return 代理类
     */
    Object getProxy();

}
