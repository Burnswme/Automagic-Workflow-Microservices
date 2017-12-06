package com.revature.aw.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.aw.domain.Swimlane;
import com.revature.aw.service.SwimlaneService;

@Aspect
@Component("aspect")
public class SwimlaneAspect {
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SwimlaneAspect.class);

	@Autowired
	private SwimlaneService service;
	
	//method is createSwimlane(Swimlane sl)
	@AfterReturning("execution(* com.revature.aw.controller.SwimlaneCtrl.createSwimlane(..))")
	public void afterGoodCreate(JoinPoint jp) {
		LOGGER.info("Good Swimlane Create: " + (Swimlane)jp.getArgs()[0]);
	}
	
	@AfterThrowing("execution(* com.revature.aw.controller.SwimlaneCtrl.createSwimlane(..))")
	public void afterBadCreate(JoinPoint jp) {
		LOGGER.info("Failed Swimlane Create: " + (Swimlane)jp.getArgs()[0]);
	}
	
	@AfterReturning("execution(* com.revature.aw.controller.SwimlaneCtrl.updateSwimlane(..))")
	public void afterGoodUpdate(JoinPoint jp) throws Throwable {
		LOGGER.info("Good Swimlane Update: " + (Swimlane)jp.getArgs()[0]);
	}
	
	@AfterThrowing("execution(* com.revature.aw.controller.SwimlaneCtrl.updateSwimlane(..))")
	public void afterBadUpdate(JoinPoint jp) throws Throwable {
		LOGGER.info("Bad Swimlane Update: " + (Swimlane)jp.getArgs()[0]);
	}
	
	@AfterReturning("execution(* com.revature.aw.controller.SwimlaneCtrl.deleteSwimlane(..))")
	public void afterGoodDelete(JoinPoint jp) {
		LOGGER.info("Good Swimlane Delete: " + (Swimlane)jp.getArgs()[0]);
	}
	
	@AfterThrowing("execution(* com.revature.aw.controller.SwimlaneCtrl.deleteSwimlane(..))")
	public void afterBadDelete(JoinPoint jp) {
		LOGGER.info("Bad Swimlane Delete: " + (Swimlane)jp.getArgs()[0]);
	}
}
