package com.riden.framework.aop;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

public class ReflectiveMethodInvocation implements MethodInvocation {


    protected final Object target;


    protected final Method method;

    protected Object[] arguments;



    public ReflectiveMethodInvocation( Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public Object[] getArguments() {
        return new Object[0];
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, arguments);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }


    @Override
    public Method getMethod() {
        return method;
    }

    public Object getTarget() {
        return target;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

}
