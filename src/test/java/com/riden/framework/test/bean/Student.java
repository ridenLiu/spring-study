package com.riden.framework.test.bean;

public class Student {

    public Student() {
        System.out.println("构造器执行");
    }

    public String sayHi(String name) {
        System.out.println("调用sayHi");
        return "hello my name is " + name;
    }


}
