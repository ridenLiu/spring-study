package com.riden.framework.aop;

public interface TargetSource {



    Class<?>[] getTargetClass();


    Object getTarget() throws Exception;





}
