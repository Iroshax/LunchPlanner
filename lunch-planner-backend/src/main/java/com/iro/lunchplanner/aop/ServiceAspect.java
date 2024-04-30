package com.iro.lunchplanner.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-27
 */
@Aspect
@Component
public class ServiceAspect {

    /**
     * logger instance
     */
    private final Logger logger = LoggerFactory.getLogger(ServiceAspect.class);

    /**
     * aop point cut to execute the trace log in the package com.iro.lunchplanner.service
     */
    @Pointcut("execution (* com.iro.lunchplanner.service.*.*(..))")
    public void execute() {
    }

    /**
     * log the trace message before execute service method
     * @param jp jointPoint
     */
    @Before("execute()")
    public void logBeforeService(JoinPoint jp) {
        String className = jp.getTarget().getClass().getName();
        logger.info("Service: {}.{} is started.",className,jp.getSignature().getName());
    }

    /**
     * log the trace message after execute service method
     * @param jp jointPoint
     */
    @After("execute()")
    public void logAfterService(JoinPoint jp) {
        String className = jp.getTarget().getClass().getName();
        logger.info("Service: {}.{} is ended.",className,jp.getSignature().getName());
    }
}
