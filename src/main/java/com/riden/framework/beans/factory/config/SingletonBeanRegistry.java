package com.riden.framework.beans.factory.config;

public interface  SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);

}
