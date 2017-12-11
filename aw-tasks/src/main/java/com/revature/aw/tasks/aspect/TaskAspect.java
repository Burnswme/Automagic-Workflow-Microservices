package com.revature.aw.tasks.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component("aspect")
public class TaskAspect {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger("task-times");
	private static final org.slf4j.Logger ERROR = LoggerFactory.getLogger("task-errors");
	
	/**
	 * An Advice to be injected Around any of the REST Endpoints. Logs time it takes to execute.
	 * @param pjp A JoinPoint to access any needed variables at runtime.
	 * @return
	 */
	@Around("execution(* com.revature.aw.tasks.controller.TaskCtrl.*(..))")
	public Object aroundControllers(ProceedingJoinPoint pjp) {
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
	@Around("execution(* com.revature.aw.tasks.service.TaskService.*(..))")
	public Object aroundServices(ProceedingJoinPoint pjp) {
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
