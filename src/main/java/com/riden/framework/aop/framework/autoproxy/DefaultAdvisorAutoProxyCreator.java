package com.riden.framework.aop.framework.autoproxy;

import com.riden.framework.beans.BeansException;
import com.riden.framework.beans.PropertyValues;
import com.riden.framework.beans.factory.BeanFactory;
import com.riden.framework.beans.factory.config.InstantiationAwareBeanPostProcessor;

public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return false;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }
}
