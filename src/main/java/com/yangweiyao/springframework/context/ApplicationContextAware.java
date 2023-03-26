package com.yangweiyao.springframework.context;

import com.yangweiyao.springframework.beans.BeansException;
import com.yangweiyao.springframework.beans.factory.Aware;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-3-26 14:19
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
