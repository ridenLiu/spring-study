package com.riden.framework.test.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyUserServiceMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            System.out.println("自定义拦截器-监控 - Begin By AOP");
            System.out.println("自定义拦截器-方法名称：" + invocation.getMethod());
            System.out.println("自定义拦截器-方法耗时：" + (System.currentTimeMillis() - start) + "ms");
            System.out.println("自定义拦截器-监控 - End\r\n");
        }
    }

}
