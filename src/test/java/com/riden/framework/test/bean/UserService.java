package com.riden.framework.test.bean;

import com.riden.framework.beans.BeansException;
import com.riden.framework.beans.context.ApplicationContext;
import com.riden.framework.beans.context.ApplicationContextAware;
import com.riden.framework.beans.factory.BeanClassLoaderAware;
import com.riden.framework.beans.factory.BeanFactory;
import com.riden.framework.beans.factory.BeanFactoryAware;
import com.riden.framework.beans.factory.BeanNameAware;

public class UserService implements IUserService, BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {
    private String userId;


    private String company;

    private String address;

    private IUserDao userDao;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("aware app: " + applicationContext);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("aware classLoader: " + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("aware beanFactory: " + beanFactory);
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("aware beanName: " + beanName);
    }

    @Override
    public String queryUserInfo(String username) {
        System.out.println("执行了queryUserInfo, 查询用户名: "+username);
        return this.toString();
    }

    private void test(){

    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "userId='" + userId + '\'' +
                ", company='" + company + '\'' +
                ", address='" + address + '\'' +
                ", userDao=" + userDao +
                '}';
    }
}
