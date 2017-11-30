package com.revature.aw;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.aw.controllers.BoardUserRoleController;
import com.revature.aw.dao.Dao;
import com.revature.aw.domain.BoardUserRole;
import com.revature.aw.domain.CompositeKey;
import com.revature.aw.services.BoardUserRoleServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AwBoardValidationApplicationTests 
{

	@Test
	public void contextLoads() 
	{
		
	}
	
	@Autowired
	private BoardUserRoleServices services;

	@Autowired
	private Dao dao;
	
	@Autowired
	private BoardUserRoleController burc;
	
	private BoardUserRole bur;
	private BoardUserRole bur2;
	
	@Before
	public void setUp()
	{
		bur = new BoardUserRole(1,1,1);
		bur2 = new BoardUserRole();
		bur2 = dao.save(bur);
	}
	
	@After
	public void tearDown()
	{
		dao.delete(bur);
	}
	
	@Test
	public void testDaoCreateBoardUserRole()
	{
		//the set up already saved bur to the database
		//if the save function works, the two objects should be equal
		assertEquals(bur.getBoardId(),bur2.getBoardId());
	}
	
	@Test
	public void testDaoFindByUserIdAndBoardId()
	{
		BoardUserRole bur3 = new BoardUserRole();
		
		bur3 = dao.findByUserIdAndBoardId(bur.getUserId(),bur.getBoardId());
		
		assertEquals(bur.getBoardId(),bur3.getBoardId());
	}
	
	@Test
	@Transactional
	public void testDaoFindByUserId()
	{
		int userId = 2;
		List<BoardUserRole> template = new ArrayList<>();
		List<BoardUserRole> result = new ArrayList<>();
		
		//create and save 3 BoardUserRole Objects to the database with the same userId
		for(int i = 2; i < 5; i++)
		{
			BoardUserRole bd = new BoardUserRole(userId,i,1);
			
			template.add(bd);
			bd = dao.save(bd);	
		}
		
		result = dao.findByUserId(userId);
		assertEquals(template.size(),result.size());
	}
	
	@Test
	public void testDaoDelete()
	{
		if(dao.findOne(bur.getBoardId()) != null)
		{
			dao.delete(bur);
			assertEquals(null,dao.findByUserIdAndBoardId(bur.getUserId(), bur.getBoardId()));
		}
		else
		{
			bur2 = dao.save(bur);
			dao.delete(bur);
			assertEquals(null,dao.findByUserIdAndBoardId(bur.getUserId(), bur.getBoardId()));
		}
	}
	@Test
	public void testServicesDeterminePrivilegesAffirmative()
	{
		int userId = 1;
		int boardId = 1;
		
		assertEquals(true,services.determinePrivileges(userId, boardId));
	}
	@Test(expected = NullPointerException.class)
	public void testServicesDeterminePrivilegesInvalidUser()
	{
		int userId = 3;
		int boardId = 5;
		
		boolean privileges = false;
		privileges = services.determinePrivileges(userId, boardId);
	}
	@Test
	@Transactional
	public void testServicesDeterminePrivilegesNotAllowed()
	{
		BoardUserRole bur4 = new BoardUserRole(3,1,0);
		dao.save(bur4);
		
		boolean privileges = true;
		privileges = services.determinePrivileges(3, 1);
		
		assertEquals(false,privileges);
		
	}

}
