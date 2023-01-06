package com.riden.framework.beans.factory.config;

public class RuntimeBeanNameReference implements BeanReference{

    private final String beanName;

    public RuntimeBeanNameReference(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public String getBeanName() {
        return beanName;
    }
}
