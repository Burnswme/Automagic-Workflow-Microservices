package com.revature.aw.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.aw.services.BoardServices;

@Aspect
@Component("aspect")
public class BoardAspect {
	
	@Autowired
	private BoardServices service;
}
