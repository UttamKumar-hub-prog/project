package com.wipro.fooddeliveryapp.order.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

	@Before("execution(* com.wipro.fooddeliveryapp.order..*(..))")
	public void logBefore(JoinPoint joinPoint) {
		log.info("Entering method: {} with arguments: {}", joinPoint.getSignature(),
				Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(value = "execution(* com.wipro.fooddeliveryapp.order..*(..))", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		log.info("Method {} executed successfully, returned: {}", joinPoint.getSignature(), result);
	}

	@AfterThrowing(value = "execution(* com.wipro.fooddeliveryapp.order..*(..))", throwing = "ex")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
		log.error("Exception in method {}: {}", joinPoint.getSignature(), ex.getMessage(), ex);
	}
}
