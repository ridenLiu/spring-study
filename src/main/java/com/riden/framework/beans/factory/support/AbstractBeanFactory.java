package com.riden.framework.beans.factory.support;

import com.riden.framework.beans.BeansException;
import com.riden.framework.beans.factory.BeanFactory;
import com.riden.framework.beans.factory.FactoryBean;
import com.riden.framework.beans.factory.HierarchicalBeanFactory;
import com.riden.framework.beans.factory.config.BeanDefinition;
import com.riden.framework.beans.factory.config.BeanPostProcessor;
import com.riden.framework.beans.factory.config.ConfigurableBeanFactory;
import com.riden.framework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象类定义模板方法
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {


    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    /**
     * 模板方法,虽然是重写,但只是确定了一个模板,内部没有实现.
     */
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
//        Object bean = getSingleton(name);
//        if (bean != null) return bean;
//        BeanDefinition beanDefinition = getBeanDefinition(name); // 核心1
//        return createBean(name, beanDefinition, args); // 核心2
    }


    protected <T> T doGetBean(final String name, final Object[] args) {
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            // 如果是 FactoryBean，则需要调用 FactoryBean#getObject
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, name);
    }

    /**
     * 如果bean实例是factory bean则尝试通过工厂bean创建对象并返回
     */
    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        Object object = getCachedObjectForFactoryBean(beanName);

        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }

        return object;
    }

    @Override
    public Object getBean(String name) {
        return getBean(name, (Object) null);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * ClassLoader to resolve bean class names with, if necessary
     */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    //...

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;


}
