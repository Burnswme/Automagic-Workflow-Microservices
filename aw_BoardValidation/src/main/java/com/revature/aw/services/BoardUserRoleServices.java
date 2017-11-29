package com.revature.aw.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.revature.aw.dao.Dao;
import com.revature.aw.domain.BoardUserRole;
import com.revature.aw.domain.CompositeKey;

@Component("BoardUserRoleServices")
@Transactional
public class BoardUserRoleServices 
{
	@Autowired
	private Dao dao;
	
	public List<BoardUserRole> findByUserId(int userId)
	{
		List<BoardUserRole> list = new ArrayList<>();
		
		list = dao.findByUserId(userId);
		if(!list.isEmpty())
		{
			return list;
		}
		else
			return null;
	}
	
	public BoardUserRole findOne(int i)
	{
		BoardUserRole bur;
		
		bur = dao.findOne(i);
		if(!bur.equals(null))
		{
			return bur;
		}
		else
			return null;
	}
	
	public BoardUserRole save(BoardUserRole bur)
	{
		BoardUserRole x;
		x = dao.save(bur);
		if(!x.equals(null))
		{
			return x;
		}
		else
			return null;
	}
	public void delete(BoardUserRole bur)
	{
		dao.delete(bur);
	}
}
