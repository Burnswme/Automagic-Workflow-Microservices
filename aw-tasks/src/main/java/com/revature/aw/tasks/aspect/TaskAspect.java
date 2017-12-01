package com.revature.aw.tasks.aspect;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.aw.tasks.dao.HistoryDAO;
import com.revature.aw.tasks.domain.History;
import com.revature.aw.tasks.domain.Task;
import com.revature.aw.tasks.service.TaskService;

@Aspect
@Component("aspect")
public class TaskAspect {
	@Autowired
	private HistoryDAO histDAO;
	
	@Autowired
	private TaskService service;
	
	//send boardId as a parameter with the request, i.e. /saveTask?boardId=1
	@After("execution(* com.revature.aw.tasks.controller.TaskCtrl.createTask(..))")
	public void afterCreate(JoinPoint jp) {
//		Task t = (Task) jp.getArgs()[0];
//		HttpServletRequest req = (HttpServletRequest)jp.getArgs()[1];
//
//		Integer userId = new Integer(req.getParameter("userId"));
//		String username = req.getParameter("userUsername");
//		Integer boardId = new Integer(req.getParameter("boardId"));
//		
//		History hist = new History();
//		hist.setBuId(userId);
//		hist.setBdId(boardId);
//		hist.setAction("User " + username + "(" + userId + ") has created a task with the name: " + t.getName());
//		hist.setTimestamp(new Date());
//		histDAO.save(hist);
	}
	
	@After("execution(* com.revature.aw.tasks.controller.TaskCtrl.deleteTask(..))")
	public void afterDelete(JoinPoint jp) {
//		Task t = (Task) jp.getArgs()[0];
//		HttpServletRequest req = (HttpServletRequest)jp.getArgs()[1];
//		
//		Integer userId = new Integer(req.getParameter("userId"));
//		String username = req.getParameter("userUsername");
//		Integer boardId = new Integer(req.getParameter("boardId"));
//		
//		History hist = new History();
//		hist.setBuId(userId);
//		hist.setBdId(boardId);
//		hist.setAction("User " + username + "(" + userId + ") has deleted a task with the name: " + t.getName());
//		hist.setTimestamp(new Date());
//		histDAO.save(hist);
	}
	
	@Before("execution(* com.revature.aw.tasks.controller.TaskCtrl.updateTask(..))")
	public void afterUpdate(JoinPoint jp) {
//		Task t = (Task) jp.getArgs()[0];
//		Task oldT = service.getTaskById(t.getId());
//		HttpServletRequest req = (HttpServletRequest)jp.getArgs()[1];
//		
//		Integer userId = new Integer(req.getParameter("userId"));
//		String username = req.getParameter("userUsername");
//		Integer boardId = new Integer(req.getParameter("boardId"));
//		
//		History hist = new History();
//		hist.setBuId(userId);
//		hist.setBdId(boardId);
//		hist.setAction("User " + username + "(" + userId + ") has updated a task from: " + oldT.getName() + "(" + oldT.getOrder() + ") to "
//				+ t.getName() + "(" + t.getOrder() + ")");
//		hist.setTimestamp(new Date());
//		histDAO.save(hist);
	}
	
}
