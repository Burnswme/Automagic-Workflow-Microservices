package com.revature.aw.aspect;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.aw.dao.HistoryDAO;
import com.revature.aw.domain.History;
import com.revature.aw.domain.Swimlane;
import com.revature.aw.service.SwimlaneService;

//CURRENTLY ONLY USING THE SAME DATABASE AS SWIMLANE
//If the History table is ever given its own database, would only need to pass the History object as a JSON to a History service that would actually handle creating history objects
@Aspect
@Component("aspect")
public class SwimlaneAspect {
	private final Logger LOGGER = Logger.getLogger(SwimlaneAspect.class);
	
	//just using DAO rather than a separate middle service class because all it really does is saving a history object to the DB
	@Autowired
	private HistoryDAO histDAO;
	
	@Autowired
	private SwimlaneService service;
	
	//method is createSwimlane(Swimlane sl)
	@After("execution(* com.revature.aw.controller.SwimlaneCtrl.createSwimlane(..))")
	public void afterCreate(JoinPoint jp) {
		Swimlane sl = (Swimlane)jp.getArgs()[0];
		HttpServletRequest req = (HttpServletRequest)jp.getArgs()[1];
		HttpSession session = req.getSession();
		
		//TESTING ONLY, DELETE WHEN SESSION ACTUALLY CONTAINS THESE
		session.setAttribute("userId", 1);
		session.setAttribute("userUsername", "AOP Test guy");
		//DELETE AFTERWARDS
		
		
		int userId = (int)session.getAttribute("userId");
		String username = (String)session.getAttribute("userUsername");
		
		History hist = new History();
		hist.setBuId(userId);
		hist.setBdId(sl.getBoardId());
		hist.setAction("User " + username + "(" + userId + ") has created a swimlane with the name: " + sl.getName());
		hist.setTimestamp(new Date());
		histDAO.save(hist);
		
//		System.out.println((Swimlane)jp.getArgs()[0]);
	}
	
	//creates history message old name/order and new name/order
	//using @Before because you need the old swimlane name BEFORE it updates it, e.g. updated 'old swimlane name' TO 'new swimlane name'
	@Before("execution(* com.revature.aw.controller.SwimlaneCtrl.updateSwimlane(..))")
	public void afterUpdate(JoinPoint jp) throws Throwable {
		Swimlane sl = (Swimlane)jp.getArgs()[0];
		Swimlane oldSl = service.findSwimlaneById(sl);
		HttpServletRequest req = (HttpServletRequest)jp.getArgs()[1];
		HttpSession session = req.getSession();
		
		//TESTING ONLY, DELETE WHEN SESSION ACTUALLY CONTAINS THESE
		session.setAttribute("userId", 1);
		session.setAttribute("userUsername", "AOP Test guy");
		//DELETE AFTERWARDS
		
		
		int userId = (int)session.getAttribute("userId");
		String username = (String)session.getAttribute("userUsername");
		
		History hist = new History();
		hist.setBuId(userId);
		hist.setBdId(sl.getBoardId());
		hist.setAction("User " + username + "(" + userId + ") has updated swimlane(order): " + oldSl.getName() + "(" + oldSl.getOrder() + ") to: " 
				+ "swimlane(order): " + sl.getName() + "(" + sl.getOrder() + ")");
		hist.setTimestamp(new Date());
		
		
		histDAO.save(hist);
	}
	
	@After("execution(* com.revature.aw.controller.SwimlaneCtrl.deleteSwimlane(..))")
	public void afterDelete(JoinPoint jp) {
		Swimlane sl = (Swimlane)jp.getArgs()[0];
		HttpServletRequest req = (HttpServletRequest)jp.getArgs()[1];
		HttpSession session = req.getSession();
		
		//TESTING ONLY, DELETE WHEN SESSION ACTUALLY CONTAINS THESE
		session.setAttribute("userId", 1);
		session.setAttribute("userUsername", "AOP Test guy");
		//DELETE AFTERWARDS
		
		
		int userId = (int)session.getAttribute("userId");
		String username = (String)session.getAttribute("userUsername");
		
		History hist = new History();
		hist.setBuId(userId);
		hist.setBdId(sl.getBoardId());
		hist.setAction("User " + username + "(" + userId + ") has deleted a swimlane with the name: " + sl.getName());
		hist.setTimestamp(new Date());
		histDAO.save(hist);
	}
}
