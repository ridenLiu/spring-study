package com.riden.framework.test.event;

import com.riden.framework.beans.context.ApplicationListener;
import com.riden.framework.beans.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("容器关闭了: " + event);
    }
}
