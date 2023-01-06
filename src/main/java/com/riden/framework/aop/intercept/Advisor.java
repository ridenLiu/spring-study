package com.riden.framework.aop.intercept;

/**
 * Advisor 访问者
 */
public interface Advisor {

    /**
     * Return the advice part of this aspect. An advice may be an
     * interceptor, a before advice, a throws' advice, etc.
     *
     * @return the advice that should apply if the pointcut matches
     * @see BeforeAdvice
     */
    Advice getAdvice();

}
