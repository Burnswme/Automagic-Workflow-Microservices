package com.revature.aw.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component("Aspect")
public class HistoryAspect {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger("history-times");

	
	//REST Controller Advice
	@Around("execution(* com.revature.aw.controllers.HistoryController.*(..))")
	public Object aroundCreate(ProceedingJoinPoint pjp) {
		long before = System.currentTimeMillis();
		Object ret = null;
		String logMsg = pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName();
		try {
			ret = pjp.proceed();
		} catch (Throwable e) {
			logMsg += " threw error: " + e.toString();
			LOGGER.error(logMsg);
			return ret;
		}
		long after = System.currentTimeMillis();
		long time = after - before;
		logMsg += " took " + time + " ms long to execute.";
		
		LOGGER.info(logMsg);
		return ret;
	}
	
	//Service class advice
	@Around("execution(* com.revature.aw.service.HistoryService.*(..))")
	public Object aroundServiceSave(ProceedingJoinPoint pjp) {
		long before = System.currentTimeMillis();
		Object ret = null;
		String logMsg = pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName();
		try {
			ret = pjp.proceed();
		} catch(Throwable e) {
			logMsg += " threw error: " + e.toString();
			LOGGER.error(logMsg);
			return ret;
		}
		long after = System.currentTimeMillis();
		long time = after - before;
		logMsg += " took " + time + " ms long to execute.";
		LOGGER.info(logMsg);
		return ret;
	}
}
