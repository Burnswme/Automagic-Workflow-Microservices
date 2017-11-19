package com.revature.aw;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
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
	private LocalDateTime date = LocalDateTime.of(2017, Month.NOVEMBER, 17, 12, 27, 28);
	
	@Before
	public void setUp()
	{
		bd = new Board(999,"test",Timestamp.valueOf(date),2);
		dao.save(bd);
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
		int id = bd.getId();
		System.out.println("Id number: " + id);
		test = dao.findOne(id);
		System.out.println("Test value: " + test);
		assertEquals(bd, test);
	}
	
	//This test should cover both create and update operations
	@Test
	public void testDaoSave()
	{
		Board test = new Board();
		test = dao.save(bd);
		
		assertEquals(bd,test);
	}
	
	/*
	 * In this test, we are passing an array of ints representing board ids to the 
	 * findById() method. It should return a List of board objects
	 */
	@Test
	public void testDaoFindById()
	{
		int [] boards = new int[3];
		ArrayList<Board> list = new ArrayList<>();
		ArrayList<Board> result = new ArrayList<>();
		
		for(int i = 0; i < boards.length; i++)
		{
	
			//Create a new board and save it to the database as well as list
			Board b = new Board(i,"test",Timestamp.valueOf(date),2);
			dao.save(b);
			list.add(b);
		}
		
		list = (ArrayList<Board>) dao.findById(boards);
		assertEquals(list,result);
		
	}
	@Test
	public void testDaoDelete()
	{
		dao.save(bd);
		
		//Delete the board
		dao.delete(bd);
		
		assertEquals(null,dao.findOne(998));
		
	}
	

}
