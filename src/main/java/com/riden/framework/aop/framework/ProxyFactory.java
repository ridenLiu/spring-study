package com.riden.framework.aop.framework;

import com.riden.framework.aop.AdvisedSupport;
import com.riden.framework.aop.AopProxy;
import com.riden.framework.aop.Cglib2AopProxy;
import com.riden.framework.aop.JdkDynamicAopProxy;

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
