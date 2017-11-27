package com.revature.aw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.aw.domain.Swimlane;
import com.revature.aw.service.SwimlaneService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SwimlaneApplicationTests {
	
	@Autowired
	private SwimlaneService service;
	
	private Swimlane test;
	
	//checks that service is properly autowired
	//creates a base swimlane for all the tests to use
	//deleted afterwards because the tests are @Transactional, it's never fully commited in the end
	@Before
	@Test
	public void initSwimlane() {
		assertNotNull("service check", service);
		
		test = new Swimlane();
		test.setName("jUnit Swimlane");
		test.setOrder(1);
		
		test = service.save(test);
		
		assertNotNull(test);
		//0 is the default value for id, so if it properly created a swimlane, it should not be 0
		assertNotEquals("create swimlane test", new Integer(0), test.getId());
	}
	
	@Test
	public void testSwimlaneUpdate() {
		test = service.save(test);
		assertNotNull(test);
		//gets original ID that is set to the object to ensure it's still the same after a save, which in this case, is an update, so it should not be a new id
		Integer originalId = test.getId();
		
		test.setName("updated jUnit swimlane");
		test = service.save(test);
		
		assertNotNull(test);
		assertEquals(originalId, test.getId());
		assertEquals("updated jUnit swimlane", test.getName());
	}
	
	//tests if test actually exists, deletes it, then checks if it no longer exists
	@Test
	public void testSwimlaneDelete() {
		test = service.save(test);
		test = service.findSwimlaneById(test);
		assertNotNull(test);
		service.delete(test);
		test = service.findSwimlaneById(test);
		assertNull(test);
	}
}
