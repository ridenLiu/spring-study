package com.riden.framework.beans.context;

import com.riden.framework.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     * refresh() 方法就是整个 Spring 容器的操作过程
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();


}
