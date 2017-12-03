package com.revature.aw.auth;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.aw.auth.dao.BoardUserDao;
import com.revature.aw.auth.domain.BoardUser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AwAuthApplicationTests {
	
	@Autowired
	private BoardUserDao dao;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void getUserTest() {
		Optional<BoardUser> optUser = dao.findByUsername("u");
		optUser.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		BoardUser testUser = new BoardUser();
		testUser.setId(10000);
		testUser.setUsername("u");
		testUser.setPassword("p");
		testUser.setFirstName("Test");
		testUser.setLastName("Tester");
		testUser.setEmail("u@p.net");
		assertTrue(testUser.getId() == optUser.get().getId());
		assertTrue(testUser.getUsername().equals(optUser.get().getUsername()));
		assertTrue(testUser.getPassword().equals(optUser.get().getPassword()));
		assertTrue(testUser.getFirstName().equals(optUser.get().getFirstName()));
		assertTrue(testUser.getLastName().equals(optUser.get().getLastName()));
		assertTrue(testUser.getEmail().equals(optUser.get().getEmail()));
	}

}
