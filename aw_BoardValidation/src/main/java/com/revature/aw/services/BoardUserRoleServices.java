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
			for(BoardUserRole bur: list)
			{	//this should remove any boards for which the user 
				//does not have viewing privileges (denoted as 0)
				if(bur.getRoleId() < 1)
				{
					list.remove(bur);
				}
			}
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
	public boolean determinePrivileges(int userId, int boardId)
	{
		boolean canView = false;
		BoardUserRole bur = new BoardUserRole();
		bur = findOne(boardId);
		
		if(!bur.equals(null))
		{
			if(bur.getRoleId() != 0)
			{
				canView = true;
			}
		}
		
		return canView;
	}
}
