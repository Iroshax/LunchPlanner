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
public class ControllerAspect {

    /**
     * logger instance
     */
    private final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    /**
     * aop point cut to execute the trace log in the package com.iro.lunchplanner.controller
     */
    @Pointcut("execution (* com.iro.lunchplanner.controller.*.*(..))")
    public void execute() {
    }

    /**
     * log the trace message before execute controller method
     * @param jp JoinPoint
     */
    @Before("execute()")
    public void logBeforeController(JoinPoint jp) {
        String className = jp.getTarget().getClass().getName();
        logger.info("Controller: {}.{} is started.",className,jp.getSignature().getName());
    }

    /**
     * log the trace message after execute controller method
     * @param jp JoinPoint
     */
    @After("execute()")
    public void logAfterController(JoinPoint jp) {
        String className = jp.getTarget().getClass().getName();
        logger.info("Controller:  {}.{} is ended.",className,jp.getSignature().getName());
    }
}
