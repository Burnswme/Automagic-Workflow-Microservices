package com.revature.aw;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.aw.controllers.BoardCtrl;
import com.revature.aw.dao.Dao;
import com.revature.aw.domain.Board;
import com.revature.aw.services.BoardServices;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AwBoardsApplicationTests 
{
	@Autowired
	private BoardServices service;
	
	@Autowired
	private Dao dao;
	
	@Autowired
	private BoardCtrl bc;
	
	private Board bd;
	private Board bd2;
	private LocalDateTime date = LocalDateTime.of(2017, Month.NOVEMBER, 17, 12, 27, 28);
	
	@Before
	public void setUp()
	{
		bd = new Board("test",Timestamp.valueOf(date),2);
		bd2 = new Board(dao.save(bd));
		
	}
	@After
	public void tearDown()
	{
		dao.delete(bd);
	}
	@Test
	public void contextLoads() 
	{
	
	}
	
	//This test should retrieve the selected board by its id
	@Test
	public void testDaoFindOne()
	{
		//Create a new board to test against the database
		Board test = new Board();
		int id = bd2.getId();
		System.out.println("Id number: " + id);
		test = dao.findOne(id);
		System.out.println("Test value: " + test);
		assertEquals(bd.getName(), test.getName());
	}
	
	//This test should cover both create and update operations
	@Test
	public void testDaoSave()
	{
		assertEquals(bd.getName(),bd2.getName());
	}
	
	/*
	 * In this test, we are passing an array of ints representing board ids to the 
	 * findById() method. It should return a List of board objects
	 */
	@Test
	@Transactional
	public void testBoardServicesGetBoardsByUserId()
	{
		int [] boards = new int[3];
		ArrayList<Board> list = new ArrayList<>();
		
		for(int i = 0; i < boards.length; i++)
		{
	
			//Create a new board and save it to the list for comparison later
			Board b = new Board(i,"test",Timestamp.valueOf(date),2);
			list.add(b);
			dao.save(b);
		}
		
		ArrayList<Board> result = new ArrayList<>();
		result = (ArrayList<Board>) service.getBoardsByBoardIds(boards);
		System.out.println("List size:" + list.size() + " " + "Result size: " + result.size());
		assertEquals(list.size(),result.size());
	}
	@Test
	public void testDaoDelete()
	{
		dao.save(bd);
		
		//Delete the board
		dao.delete(bd);
		
		assertEquals(null,dao.findOne(bd.getId()));
		
	}
	

}
