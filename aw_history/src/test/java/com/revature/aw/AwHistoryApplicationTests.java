package com.revature.aw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.aw.domain.History;
import com.revature.aw.service.HistoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AwHistoryApplicationTests {

	@Autowired
	private HistoryService service;
	
	private History hist;
	private final String testAction = "jUnit test action";
	private final int testBoardId = 999999999;
	
	//also counts as Create Test
	@Before
	@Test
	public void setUp() {
		hist = new History();
		hist.setAction(testAction);
		hist.setBoardId(testBoardId);
		//should be initially 0
		assertEquals(0, hist.getId());
		hist = service.createHistory(hist);
		
		//should not be equal to 0, since it gets set a generated Id
		assertNotEquals(0, hist.getId());
		
		History hist2 = new History();
		hist2.setAction(testAction);
		hist2.setBoardId(testBoardId);
		hist2 = service.createHistory(hist2);
	}
	
	//after setUp, should get a history list of size 2
	@Test
	public void testGetHistory() {
		List<History> histList = service.getHistoryByBoardId(testBoardId);
		assertEquals(2, histList.size());
	}

}
