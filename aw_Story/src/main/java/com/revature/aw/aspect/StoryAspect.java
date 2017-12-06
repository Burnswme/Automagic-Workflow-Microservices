package com.revature.aw.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.aw.domain.Story;
import com.revature.aw.services.StoryServices;

@Aspect
@Component("aspect")
public class StoryAspect {
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(StoryAspect.class);
	
	@Autowired
	private StoryServices service;
	
	
	@AfterReturning("execution(* com.revature.aw.controllers.StoryCtrl.createStory(..))")
	public void afterGoodCreate(JoinPoint jp) {
		LOGGER.info("Good Create Story: " + (Story)jp.getArgs()[0]);
	}
	
	@AfterThrowing("execution(* com.revature.aw.controllers.StoryCtrl.createStory(..))")
	public void afterBadCreate(JoinPoint jp) {
		LOGGER.info("Good Create Story: " + (Story)jp.getArgs()[0]);
	}
	
	@AfterReturning("execution(* com.revature.aw.controllers.StoryCtrl.updateStory(..))")
	public void afterGoodUpdate(JoinPoint jp) {
		LOGGER.info("Good Update Story: " + (Story)jp.getArgs()[0]);
	}
	
	@AfterThrowing("execution(* com.revature.aw.controllers.StoryCtrl.updateStory(..))")
	public void afterBadUpdate(JoinPoint jp) {
		LOGGER.info("Bad Update Story: " + (Story)jp.getArgs()[0]);
	}
	
	@AfterReturning("execution(* com.revature.aw.controllers.StoryCtrl.deleteStory(..))")
	public void afterGoodDelete(JoinPoint jp) {
		LOGGER.info("Good Delete Story: " + (Story)jp.getArgs()[0]);
	}
	
	@AfterThrowing("execution(* com.revature.aw.controllers.StoryCtrl.deleteStory(..))")
	public void afterBadDelete(JoinPoint jp) {
		LOGGER.info("Bad Delete Story: " + (Story)jp.getArgs()[0]);
	}
}
