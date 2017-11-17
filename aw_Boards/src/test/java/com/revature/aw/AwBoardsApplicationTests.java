package com.revature.aw;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;

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
	
	@Test
	public void contextLoads() 
	{
	
	}
	
	//This test should retrieve the selected board by its id
	@Test
	public void testDaoFindOne()
	{
		String time = "17-NOV-17 12.27.28[.084808000] PM";
		bd = new Board(999,"test", Timestamp.valueOf(time),2);
		
		Board test = new Board();
		int id = bd.getId();
		test = dao.findOne(id);
		
		assertEquals(bd, test);
	}
	
	//This test should cover both create and update operations
	@Test
	public void testDaoSave()
	{
		String time = "17-NOV-17 12.27.28[.084808000] PM";
		bd = new Board(100,"test board",Timestamp.valueOf(time),3);
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
		int [] boards = {900,901,902};
		String time = "17-NOV-17 12.27.28[.084808000] PM";
		ArrayList<Board> list = new ArrayList<>();
		ArrayList<Board> result = new ArrayList<>();
		
		for(int i = 900; i < 903; i++)
		{
			//Delete any previous instances of the boards from previous tests
			Board d = new Board();
			d.setId(i);
			dao.delete(d);
			
			//Create a new board and save it to the database as well as list
			Board b = new Board(i,"test",Timestamp.valueOf(time),2);
			dao.save(b);
			list.add(b);
		}
		
		list = (ArrayList<Board>) dao.findById(boards);
		assertEquals(list,result);
		
	}
	@Test
	public void testDaoDelete()
	{
		//Create a new board and persist it to the database
		String time = "17-NOV-17 12.27.28[.084808000] PM";
		bd = new Board(998,"test",Timestamp.valueOf(time),2);
		dao.save(bd);
		
		//Delete the board
		dao.delete(bd);
		
		assertEquals(null,dao.findOne(998));
		
	}
	

}
