package com.riden.framework.beans.factory.config;

import com.riden.framework.beans.BeansException;
import com.riden.framework.beans.PropertyValues;
import com.riden.framework.beans.factory.BeanFactoryAware;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor, BeanFactoryAware {

    /**
     * Apply this BeanPostProcessor <i>before the target bean gets instantiated</i>.
     * The returned bean object may be a proxy to use instead of the target bean,
     * effectively suppressing default instantiation of the target bean.
     * <p>
     * 在 Bean 对象执行初始化方法之前，执行此方法
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    /**
     * Post-process the given property values before the factory applies them
     * to the given bean. Allows for checking whether all dependencies have been
     * satisfied, for example based on a "Required" annotation on bean property setters.
     * <p>
     * 在 Bean 对象实例化完成后，设置属性操作之前执行此方法
     */
    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;

    /**
     * Post-process the given property values before the factory applies them
     * to the given bean. Allows for checking whether all dependencies have been
     * satisfied, for example based on a "Required" annotation on bean property setters.
     * <p>
     * 在 Bean 对象实例化完成后，设置属性操作之前执行此方法
     */
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;

    /**
     * 在 Spring 中由 SmartInstantiationAwareBeanPostProcessor#getEarlyBeanReference 提供
     */
    default Object getEarlyBeanReference(Object bean, String beanName) {
        return bean;
    }

}
