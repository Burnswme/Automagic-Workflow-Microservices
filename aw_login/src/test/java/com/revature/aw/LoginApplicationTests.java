package com.revature.aw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.aw.domain.BoardUser;
import com.revature.aw.service.LoginService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginApplicationTests {
	
	@Autowired
	private LoginService service;
	
	@Test
	public void contextLoads() {
		assertNotNull(service);
	}

	
	//TEST FOR VALID USER
	@Test
	public void testValidLogin() {
		BoardUser bu = new BoardUser();
		bu.setUsername("hello");
		bu.setPassword("world");
		bu = service.login(bu);
		assertEquals("billy", bu.getFirstName());
	}
	//TEST FOR INVALID USER
	@Test
	public void testInvalidLogin() {
		BoardUser bu = new BoardUser();
		bu.setUsername("hello");
		bu.setPassword("asdad");
		bu = service.login(bu);
		assertNull(bu);
	}
}
