package com.wipro.fooddeliveryapp.restuarant.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

	@Before("execution(* com.wipro.fooddeliveryapp.restuarant.controlller.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		log.info("➡ Entering method: {} with args: {}", joinPoint.getSignature(), joinPoint.getArgs());
	}

	@AfterReturning(pointcut = "execution(* com.wipro.fooddeliveryapp.restuarant.controlller.*.*(..))", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		log.info("✅ Method executed: {} with result: {}", joinPoint.getSignature(), result);
	}

	@Around("execution(* com.wipro.fooddeliveryapp.restuarant.controlller.*.*(..))")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		log.info("⏱ Method {} executed in {} ms", joinPoint.getSignature(), executionTime);
		return proceed;
	}

	@AfterThrowing(pointcut = "execution(* com.wipro.fooddeliveryapp.restuarant.controlller.*.*(..))", throwing = "ex")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
		log.error("❌ Exception in method: {} with message: {}", joinPoint.getSignature(), ex.getMessage());
	}
}
