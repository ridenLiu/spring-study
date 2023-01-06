package com.riden.framework.beans.factory.support;

import cn.hutool.core.lang.Assert;
import com.riden.framework.beans.BeansException;
import com.riden.framework.beans.factory.ConfigurableListableBeanFactory;
import com.riden.framework.beans.factory.config.BeanDefinition;
import com.riden.framework.beans.factory.config.BeanPostProcessor;

import java.util.*;

/**
 * 核心实现类
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory
        implements ConfigurableListableBeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private List<String> beanDefinitionNames = new ArrayList<>(256);

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        Assert.notNull(beanName, "Bean name must not be null");
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
        return beanDefinition;
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
//        List<String> beanNames = new ArrayList<>(this.beanDefinitionNames);
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        List<String> res = new ArrayList<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class<?> beanClass = beanDefinition.getBeanClass();
            if (isSameBeanType(beanClass, type)) res.add(beanName);
        });
        return res.toArray(new String[0]);
    }

    private boolean isSameBeanType(Class<?> subCls, Class<?> superCls) {
        if (subCls.equals(superCls)) return true;
        Class<?>[] interfaces = subCls.getInterfaces();
        return Arrays.asList(interfaces).contains(superCls);
    }


    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        String[] beanNames = getBeanNamesForType(type);
        Map<String, T> result = new LinkedHashMap<>(beanNames.length);
        for (String beanName : beanNames) {
            Object beanInstance = getBean(beanName);
            result.put(beanName, (T) beanInstance);
        }
        return result;
    }



}
