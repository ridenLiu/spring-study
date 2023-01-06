package com.riden.framework.beans.factory;

import com.riden.framework.beans.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    String[] getBeanNamesForType(Class<?> type);

    /**
     * Return the bean instances that match the given object type (including subclasses),
     * judging from either bean definitions or the value of getObjectType in the case of FactoryBeans.
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

}
