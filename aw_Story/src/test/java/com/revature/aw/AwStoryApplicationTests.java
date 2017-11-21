package com.revature.aw;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.aw.dao.Dao;
import com.revature.aw.domain.Story;
import com.revature.aw.services.StoryServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AwStoryApplicationTests 
{

	@Autowired
	private StoryServices service;
	
	@Autowired
	private Dao dao;
	
	private Story story;
	
	private LocalDateTime date;
	
	@Before
	public void setUp()
	{
		date = LocalDateTime.of(2017, Month.NOVEMBER, 17, 12, 27, 28);
		story = new Story("Test","Test Story",4,Timestamp.valueOf(date),1);
	}
	
	@Test
	public void contextLoads() 
	{
		
	}
	
	@Test
	@Transactional
	public void testDaoSave()
	{
		Story st = new Story();
		st = dao.save(story);
		
		assertEquals(story.getTitle(),st.getTitle());
	}
	@Test
	@Transactional
	public void testDaoDelete()
	{
		dao.save(story);
		dao.delete(story);
		
		assertEquals(null,dao.findOne(story.getId()));
	}
	
	@Test
	@Transactional
	public void testDaoFindOne()
	{
		Story st = new Story();
		dao.save(story);
		st = dao.findOne(story.getId());
		assertEquals(story.getId(),st.getId());
	}
	
	@Test
	@Transactional
	public void testStoryServicesGetStories()
	{
		ArrayList<Story> list = new ArrayList<>();
		ArrayList<Story> result = new ArrayList<>();
		int [] ids = new int[3];
		
		for(int i = 0; i < 3; i++)
		{
			Story st = new Story("Services Test","Test Services Story",6,Timestamp.valueOf(date),3);
			dao.save(st);
			list.add(st);
			ids[i] = st.getId();
		}
		
		result = (ArrayList<Story>) service.getStories(ids);
		System.out.println(list.size() + " " + result.size());
		assertEquals(list.isEmpty(),result.isEmpty());
	}
	

}
