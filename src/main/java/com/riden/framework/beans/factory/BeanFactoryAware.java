package com.riden.framework.beans.factory;

import com.riden.framework.beans.BeansException;

public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;


}
