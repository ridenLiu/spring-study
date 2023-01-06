package com.riden.framework.beans.context;

import com.riden.framework.beans.BeansException;
import com.riden.framework.beans.factory.Aware;

public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
