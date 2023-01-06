package com.riden.framework.aop.target;

import com.riden.framework.aop.TargetSource;
import com.riden.framework.util.ClassUtils;

public class MySimpleBeanTargetSource implements TargetSource {


    private final Object target;


    public MySimpleBeanTargetSource(Object target) {
        this.target = target;
    }

    @Override
    public Class<?>[] getTargetClass() {
        Class<?> clazz = this.target.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }


    @Override
    public Object getTarget() {
        return target;
    }


}
