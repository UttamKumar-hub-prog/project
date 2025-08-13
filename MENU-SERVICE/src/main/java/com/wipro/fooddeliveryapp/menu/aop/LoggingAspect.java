package com.wipro.fooddeliveryapp.menu.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(com.wipro.fooddeliveryapp.menu.controllerr..*)")
    public void controllerPackagePointcut() {}

    @Pointcut("within(com.wipro.fooddeliveryapp.menu.servicess..*)")
    public void servicePackagePointcut() {}

    @Before("controllerPackagePointcut() || servicePackagePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments {}", 
                joinPoint.getSignature().toShortString(), 
                joinPoint.getArgs());
    }

    @After("controllerPackagePointcut() || servicePackagePointcut()")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Exiting method: {}", joinPoint.getSignature().toShortString());
    }

    @Around("controllerPackagePointcut() || servicePackagePointcut()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logger.info("{} executed in {} ms", joinPoint.getSignature(), executionTime);
        return proceed;
    }

    @AfterThrowing(pointcut = "controllerPackagePointcut() || servicePackagePointcut()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.error("Exception in method: {} with cause = {}", 
                joinPoint.getSignature().toShortString(), 
                ex.getMessage(), ex);
    }
}
