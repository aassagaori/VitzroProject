package com.vitzro.config.handler;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;


public class CustomSpringAyncUncaughtExceptonHandler implements AsyncUncaughtExceptionHandler{

	private static final Logger logger = LoggerFactory.getLogger(CustomSpringAyncUncaughtExceptonHandler.class);
 
	 @Override
	    public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
		 
		 logger.error("{} | {} |",method.toString(),obj,throwable);
	      
	 }
}
