package com.project1.ecommerce.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceMonitorAspect {

    
    @Around("execution(* com.project1.ecommerce.service.*.*(..))")
    public Object monitorTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        
        Object result = joinPoint.proceed();
        
        long executionTime = System.currentTimeMillis() - start;
        
        System.out.println("--> [Performance Monitor]: Method [" + joinPoint.getSignature().getName() + "] executed in: " + executionTime + " ms");
        
        return result;
    }
}