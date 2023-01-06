package com.riden.framework.beans.factory;

import com.riden.framework.beans.BeansException;

public interface BeanFactory {

    Object getBean(String name);

    Object getBean(String name, Object... args) throws BeansException;

}
