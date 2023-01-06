package com.riden.framework.aop.framework;

import com.riden.framework.aop.AdvisedSupport;
import com.riden.framework.aop.AopProxy;
import com.riden.framework.aop.Cglib2AopProxy;
import com.riden.framework.aop.JdkDynamicAopProxy;

/**
 * 其实这个代理工厂主要解决的是关于 JDK 和 Cglib 两种代理的选择问题，有了代理工厂就可以按照不同的创建需求进行控制。
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() throws Exception {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) return new Cglib2AopProxy(advisedSupport);
        return new JdkDynamicAopProxy(advisedSupport);
    }


}
