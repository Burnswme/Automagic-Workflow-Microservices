package com.revature.aw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.aw.dao.SwimlaneDAO;
import com.revature.aw.domain.Swimlane;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SwimlaneApplicationTests {
	
	@Autowired
	private SwimlaneDAO dao;
	
	private Swimlane test;
	
	@Test
	public void contextLoads() {
		assertNotNull(dao);
	}
	
	@Before
	public void initSwimlane() {
		
	}
	
	@Test
	public void testSwimlaneSave() {
		Swimlane test = new Swimlane();
		test.setName("jUnit swimlane");
		
		test = dao.save(test);
		
		assertNotNull(test);
		//means id would have been set, since 0 is the default value for an instance variable
		assertNotEquals(new Integer(0), test.getId());
	}
	
	@Test
	public void testSwimlaneUpdate() {
		Swimlane test = new Swimlane();
		test.setName("jUnit swimlane");
		
		test = dao.save(test);
		
		assertNotNull(test);
		//gets original ID that is set to the object to ensure it's still the same after a save
		Integer originalId = test.getId();
		
		test.setName("updated jUnit swimlane");
		test = dao.save(test);
		
		assertNotNull(test);
		assertEquals(originalId, test.getId());
		assertEquals("updated jUnit swimlane", test.getName());
	}
	
	//will rely on create swimlane working, as it needs to create something to delete
	@Test
	public void testSwimlaneDelete() {
		Swimlane test = new Swimlane();
//		test
	}
}
