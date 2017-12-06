package com.revature.aw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Ignore;
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
	
	private Swimlane test = mock(Swimlane.class);
	
	
	private final int testId = 99999999;//for a universal board id to test on
	
	private final String originalName = "TEST SWIMLANE";
	private final String updatedName = "UPDATED TEST SWIMLANE";
	
	//checks that service is properly autowired
	//creates a base swimlane for all the tests to use
	//deleted afterwards because the tests are @Transactional, it's never fully commited in the end
	@Before
	public void initSwimlane() {
		when(test.getBoardId()).thenReturn(testId);
		when(test.getName()).thenReturn("TEST SWIMLANE");
	}
	
	@Test
	public void alwaysTrue() {
		assertTrue(true);
	}
	
	@Test
	public void testSwimlaneUpdate() {
		//gets original ID that is set to the object to ensure it's still the same after a save, which in this case, is an update, so it should not be a new id
		test = service.save(test);
		int originalId = test.getId();
		
		test.setName(updatedName);
		test = service.save(test);
		
		//tests ids because since it is an update, so the id should stay the same
		assertEquals((Integer)originalId, test.getId());
		//the name should obviously be different
		assertNotEquals(originalName, test.getName());
		assertEquals(updatedName, test.getName());
	}
	
	//tests if test actually exists, deletes it, then checks if it no longer exists
	@Test
	@Ignore
	public void testSwimlaneDelete() {
		test = service.findSwimlaneById(test);
		assertNotNull(test);
		service.delete(test);
		test = service.findSwimlaneById(test);
		assertNull(test);
	}
	
	//testing getting a list of swimlanes by board id
	@Test
	@Ignore
	public void testSwimlanesFind() {
		Swimlane sl1 = new Swimlane();
		sl1.setBoardId(testId);
		Swimlane sl2 = new Swimlane();
		sl2.setBoardId(testId);
		service.save(sl1);
		service.save(sl2);
		
		//twice so it will create TWO
		List<Swimlane> swimlanes = service.findSwimlanesByBoardId(testId);
		assertEquals(2, swimlanes.size());
	}
}
