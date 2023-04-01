package com.yangweiyao.springframework.aop;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-4-1 14:42
 */
public interface ClassFilter {

    /**
     * Should the pointcut apply to the given interface or target class?
     * @param clazz the candidate target class
     * @return whether the advice should apply to the given target class
     */
    boolean matches(Class<?> clazz);

}
