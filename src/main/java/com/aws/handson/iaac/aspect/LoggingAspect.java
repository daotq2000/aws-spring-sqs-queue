package com.aws.handson.iaac.aspect;

import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	 private Logger myLogger = Logger.getLogger(getClass().getName());

	    @Pointcut("execution(* com.meesho.springbootEcom.controller.*.*(..))")
	    private void forControllerPackage() {
	        myLogger.info("=====>> in @forControllerPackage(): calling method: ");
	    }

	    @Pointcut("execution(* com.meesho.springbootEcom.service.*.*(..))")
	    private void forServicePackage() {}

	    @Pointcut("execution(* com.meesho.springbootEcom.dto.*.*(..))")
	    private void forDtoPackage() {}

	    @Pointcut("forControllerPackage() || forServicePackage() || forDtoPackage()")
	    private void forAppFlow() {}

	    @Before("forAppFlow()")
	    public void before(JoinPoint theJoinPoint) {

	        String theMethod = theJoinPoint.getSignature().toShortString();
	        myLogger.info("=====>> in @Before: calling method: " + theMethod);

	 

	        Object[] args = theJoinPoint.getArgs();

	 

	        for (Object tempArg : args) {
	            myLogger.info("=====>> argument: " + tempArg);
	        }

	    }

	 

	    
	    @AfterReturning(
	            pointcut="forAppFlow()",
	            returning="theResult"
	            )
	    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {

	 

	        String theMethod = theJoinPoint.getSignature().toShortString();
	        myLogger.info("=====>> in @AfterReturning: from method: " + theMethod);

	 

	        myLogger.info("=====>> result: " + theResult);

	    }
	}


