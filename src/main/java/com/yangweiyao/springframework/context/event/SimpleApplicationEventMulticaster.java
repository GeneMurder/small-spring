package com.yangweiyao.springframework.context.event;

import com.yangweiyao.springframework.beans.factory.BeanFactory;
import com.yangweiyao.springframework.context.ApplicationEvent;
import com.yangweiyao.springframework.context.ApplicationListener;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-3-26 17:15
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener<ApplicationEvent> listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }

}
