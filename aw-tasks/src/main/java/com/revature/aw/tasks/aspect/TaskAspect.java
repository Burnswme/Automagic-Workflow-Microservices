package com.revature.aw.tasks.aspect;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
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
		Task t = (Task) jp.getArgs()[0];
		HttpServletRequest req = (HttpServletRequest)jp.getArgs()[1];
		HttpSession session = req.getSession();
		
		//TESTING ONLY, DELETE WHEN SESSION ACTUALLY CONTAINS THESE
		session.setAttribute("userId", 1);
		session.setAttribute("userUsername", "AOP Test guy");
		//DELETE AFTERWARDS
		
		
		int userId = (int)session.getAttribute("userId");
		String username = (String)session.getAttribute("userUsername");
		Integer boardId = new Integer(req.getParameter("boardId"));
		
		History hist = new History();
		hist.setBuId(userId);
		hist.setBdId(boardId);
		hist.setAction("User " + username + "(" + userId + ") has created a task with the name: " + t.getName());
		hist.setTimestamp(new Date());
		histDAO.save(hist);
	}
	
	@After("execution(* com.revature.aw.tasks.controller.TaskCtrl.deleteTask(..))")
	public void afterDelete(JoinPoint jp) {
		
	}
	
}
