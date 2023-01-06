package com.riden.framework.test;

import com.riden.framework.aop.AdvisedSupport;
import com.riden.framework.aop.AspectJExpressionPointcut;
import com.riden.framework.aop.Cglib2AopProxy;
import com.riden.framework.aop.JdkDynamicAopProxy;
import com.riden.framework.aop.target.MySimpleBeanTargetSource;
import com.riden.framework.test.bean.IUserService;
import com.riden.framework.test.bean.Student;
import com.riden.framework.test.bean.UserService;
import com.riden.framework.test.common.MyUserServiceMethodInterceptor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class AopTest {


    public static void main(String[] args) throws Exception {
        aopTest_JdkReflect();
//        aopTest_Cglib();
    }

    static void aopTest_Cglib() throws Exception {
        Student student = new Student();
        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new MySimpleBeanTargetSource(student));
        advisedSupport.setMethodInterceptor(new MyUserServiceMethodInterceptor());
        advisedSupport.setMethodMatcher(
                new AspectJExpressionPointcut("execution(* com.riden.framework.test.bean.Student.*(..))"));
        // 代理对象(Cglib2AopProxy)
        Student proxy_cglib = (Student) new Cglib2AopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_cglib.sayHi("cglib调用"));

    }

    static void aopTest_JdkReflect() throws Exception {
        // 目标对象
        IUserService userService = new UserService();

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new MySimpleBeanTargetSource(userService));
        advisedSupport.setMethodInterceptor(new MyUserServiceMethodInterceptor());
        advisedSupport.setMethodMatcher(
                new AspectJExpressionPointcut("execution(* com.riden.framework.test.bean.IUserService.*(..))"));


        // 代理对象(JdkDynamicAopProxy)
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_jdk.queryUserInfo("jdk调用"));


    }

    static void proxyTest_JdkReflectProxy() {
        IUserService userService = new UserService();
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            System.out.println("方案执行前");
            Object res = method.invoke(userService, args);
            System.out.println("方法执行后");
            return res;
        };

        ClassLoader curClassLoader = Thread.currentThread().getContextClassLoader();
        IUserService proxyObj = (IUserService) Proxy.newProxyInstance(curClassLoader, new Class[]{IUserService.class}, invocationHandler);
        System.out.println(proxyObj.queryUserInfo("java 反射 调用"));

    }

    static void proxyTest_Cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {

            System.out.println("方法拦截: before method run...");
            Object result = proxy.invokeSuper(obj, args);
            System.out.println("方法拦截: after method run...");
            return result;
        });
        IUserService sample = (IUserService) enhancer.create();
        sample.queryUserInfo("cglib 调用");

    }


}
