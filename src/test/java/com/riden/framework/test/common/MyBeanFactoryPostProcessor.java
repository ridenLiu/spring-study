package com.riden.framework.test.common;

import com.riden.framework.beans.BeansException;
import com.riden.framework.beans.PropertyValue;
import com.riden.framework.beans.PropertyValues;
import com.riden.framework.beans.factory.ConfigurableListableBeanFactory;
import com.riden.framework.beans.factory.config.BeanDefinition;
import com.riden.framework.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "睿至11"));
    }
}
