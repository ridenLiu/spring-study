package com.riden.framework.beans.context;

import java.util.EventListener;
import java.util.function.Consumer;

public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);

}
