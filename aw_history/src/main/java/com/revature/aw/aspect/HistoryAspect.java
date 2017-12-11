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
	private static final org.slf4j.Logger ERROR = LoggerFactory.getLogger("history-errors");
	
	/**
	 * An Advice to be injected Around any of the REST Endpoints. Logs time it takes to execute.
	 * @param pjp A JoinPoint to access any needed variables at runtime.
	 * @return
	 */
	@Around("execution(* com.revature.aw.controllers.HistoryController.*(..))")
	public Object aroundCreate(ProceedingJoinPoint pjp) {
		long before = System.currentTimeMillis();
		Object ret = null;
		String logMsg = pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName();
		try {
			ret = pjp.proceed();
		} catch (Throwable e) {
			logMsg += " threw error: " + e.toString();
			ERROR.error(logMsg);
			return ret;
		}
		long after = System.currentTimeMillis();
		long time = after - before;
		logMsg += " took " + time + " ms long to execute.";
		
		LOGGER.info(logMsg);
		return ret;
	}
	
	/**
	 * An Advice to be injected Around any of the Service methods. Logs time it takes to execute.
	 * @param pjp A JoinPoint to access any needed variables at runtime.
	 * @return
	 */
	@Around("execution(* com.revature.aw.service.HistoryService.*(..))")
	public Object aroundServiceSave(ProceedingJoinPoint pjp) {
		long before = System.currentTimeMillis();
		Object ret = null;
		String logMsg = pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName();
		try {
			ret = pjp.proceed();
		} catch(Throwable e) {
			logMsg += " threw error: " + e.toString();
			ERROR.error(logMsg);
			return ret;
		}
		long after = System.currentTimeMillis();
		long time = after - before;
		logMsg += " took " + time + " ms long to execute.";
		LOGGER.info(logMsg);
		return ret;
	}
}
