package com.revature.aw.tasks.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.aw.tasks.domain.Task;
import com.revature.aw.tasks.service.TaskService;

@Aspect
@Component("aspect")
public class TaskAspect {
	
	@Autowired
	private TaskService service;
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TaskAspect.class);
	
	//send boardId as a parameter with the request, i.e. /saveTask?boardId=1
	@AfterReturning("execution(* com.revature.aw.tasks.controller.TaskCtrl.createTask(..))")
	public void afterGoodCreate(JoinPoint jp) {
		LOGGER.info("Good Task Create: " + (Task)jp.getArgs()[0]);
	}
	
	@AfterThrowing("execution(* com.revature.aw.tasks.controller.TaskCtrl.createTask(..))")
	public void afterBadCreate(JoinPoint jp) {
		LOGGER.info("Good Task Create: " + (Task)jp.getArgs()[0]);
	}
	
	@AfterReturning("execution(* com.revature.aw.tasks.controller.TaskCtrl.updateTask(..))")
	public void afterGoodUpdate(JoinPoint jp) {
		LOGGER.info("Good Task Update: " + (Task)jp.getArgs()[0]);
	}
	
	@AfterThrowing("execution(* com.revature.aw.tasks.controller.TaskCtrl.updateTask(..))")
	public void afterBadUpdate(JoinPoint jp) {
		LOGGER.info("Bad Task Update: " + (Task)jp.getArgs()[0]);
	}
	
	@AfterReturning("execution(* com.revature.aw.tasks.controller.TaskCtrl.deleteTask(..))")
	public void afterGoodDelete(JoinPoint jp) {
		LOGGER.info("Good Task Delete: " + (Task)jp.getArgs()[0]);
	}
	
	@AfterThrowing("execution(* com.revature.aw.tasks.controller.TaskCtrl.deleteTask(..))")
	public void afterBadDelete(JoinPoint jp) {
		LOGGER.info("Bad Task Delete: " + (Task)jp.getArgs()[0]);
	}
	
	
}
