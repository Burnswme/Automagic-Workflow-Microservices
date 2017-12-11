package com.revature.aw.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.aw.services.BoardServices;

@Aspect
@Component("aspect")
public class BoardAspect {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger("board-times");
	private static final org.slf4j.Logger ERROR = LoggerFactory.getLogger("board-errors");
	/**
	 * An Advice to be injected Around any of the REST Endpoints. Logs time it takes to execute.
	 * @param pjp A JoinPoint to access any needed variables at runtime.
	 * @return
	 */
	@Around("execution(* com.revature.aw.controllers.BoardCtrl.*(..))")
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
	@Around("execution(* com.revature.aw.services.BoardServices.*(..))")
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
