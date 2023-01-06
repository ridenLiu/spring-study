package com.riden.framework.test.bean;

import com.riden.framework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * 这是一个实现接口 FactoryBean 的代理类 ProxyBeanFactory 名称，主要是模拟了 UserDao 的原有功能，
 * 类似于 MyBatis 框架中的代理操作。
 * getObject() 中提供的就是一个 InvocationHandler 的代理对象，当有方法调用的时候，则执行代理对象的功能
 */
public class ProxyBeanFactory implements FactoryBean<IUserDao> {
    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler handler = ((proxy, method, args) -> {
            if ("toString".equals(method.getName())) {
                return "userDao to String";
            }

            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("10001", "小傅哥");
            hashMap.put("10002", "八杯水");
            hashMap.put("10003", "阿毛");
            if (args == null) args = new Object[0];
            return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());
        });

        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
