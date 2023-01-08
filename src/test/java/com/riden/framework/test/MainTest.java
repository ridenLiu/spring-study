package com.riden.framework.test;

import com.riden.framework.beans.PropertyValue;
import com.riden.framework.beans.PropertyValues;
import com.riden.framework.beans.context.support.ClassPathXmlApplicationContext;
import com.riden.framework.beans.core.io.ClassPathResource;
import com.riden.framework.beans.core.io.Resource;
import com.riden.framework.beans.factory.config.BeanDefinition;
import com.riden.framework.beans.factory.config.RuntimeBeanNameReference;
import com.riden.framework.beans.factory.support.BeanDefinitionReader;
import com.riden.framework.beans.factory.support.DefaultListableBeanFactory;
import com.riden.framework.beans.factory.xml.XmlBeanDefinitionReader;
import com.riden.framework.test.bean.IUserDao;
import com.riden.framework.test.bean.UserService;
import com.riden.framework.test.event.CustomEvent;

public class MainTest  {

    public static void main(String[] args) {
        MainTest m = new MainTest();
        m.test_event();
    }

    public void test_aop() {

    }
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext(new String[]{"classpath:spring-config.xml"});


        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }


    public void test_appContext() {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext(new String[]{"classpath:spring-config.xml"});
//        applicationContext.registerShutdownHook();

        UserService userService = (UserService) applicationContext.getBean("userService");
//        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.queryUserInfo("");
    }

    public void test_XmlConfig() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        Resource xmlConfig = new ClassPathResource("spring-config.xml");
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions(xmlConfig);


        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo("");


    }


    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(IUserDao.class));

        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new RuntimeBeanNameReference("userDao")));

        // 4. UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5. UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo("");
    }


}
