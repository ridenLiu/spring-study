package com.riden.framework.beans.factory;

import com.riden.framework.beans.BeansException;
import com.riden.framework.beans.factory.config.AutowireCapableBeanFactory;
import com.riden.framework.beans.factory.config.BeanDefinition;
import com.riden.framework.beans.factory.config.BeanPostProcessor;
import com.riden.framework.beans.factory.config.ConfigurableBeanFactory;

import java.util.Map;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    void preInstantiateSingletons() throws BeansException;


    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;





}
