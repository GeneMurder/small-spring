package com.yangweiyao.springframework.context;

import com.yangweiyao.springframework.beans.factory.HierarchicalBeanFactory;
import com.yangweiyao.springframework.beans.factory.ListableBeanFactory;
import com.yangweiyao.springframework.core.io.ResourceLoader;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
