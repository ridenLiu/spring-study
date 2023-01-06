package com.riden.framework.beans.context.event;

import com.riden.framework.beans.context.ApplicationEvent;
import com.riden.framework.beans.context.ApplicationListener;
import com.riden.framework.beans.factory.BeanFactory;

import java.util.Collection;
import java.util.List;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {


    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        Collection<ApplicationListener> listeners = getApplicationListeners(event);
        for (ApplicationListener listener : listeners) {
            listener.onApplicationEvent(event);
        }
    }


}
