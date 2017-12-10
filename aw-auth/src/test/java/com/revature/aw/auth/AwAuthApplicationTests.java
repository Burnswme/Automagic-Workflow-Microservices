package com.revature.aw.auth;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.aw.auth.dao.LoginDao;
import com.revature.aw.auth.domain.Login;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AwAuthApplicationTests {
	
	@Autowired
	private LoginDao dao;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void getUserTest() {
		Optional<Login> optUser = dao.findByUsername("u");
		optUser.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		Login testUser = new Login();
		testUser.setUsername("u");
		testUser.setPassword("cA==");
		assertTrue(testUser.getUsername().equals(optUser.get().getUsername()));
		assertTrue(testUser.getPassword().equals(optUser.get().getPassword()));
	}

}
