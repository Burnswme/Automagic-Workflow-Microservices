package com.revature.aw.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aw.domain.BoardUserRole;
import com.revature.aw.services.BoardUserRoleServices;

@RestController
public class BoardUserRoleController 
{
	@Autowired
	private BoardUserRoleServices services;
	
	@GetMapping("/getUserBoards")
	@ResponseBody
	public ResponseEntity<Object> getUserBoards(@PathVariable("userId")int userId, HttpServletRequest req)
	{
		List<BoardUserRole> list;
		list = services.findByUserId(userId);
		if(!list.isEmpty())
		{
			return (ResponseEntity<Object>) list;
		}
		else
			return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@GetMapping("/createUserBoardRole")
	@ResponseBody
	public ResponseEntity<Object> createUserBoardRole(@PathVariable("userRole")BoardUserRole bur,HttpServletRequest req )
	{
		BoardUserRole x;
		x = services.save(bur);
		if(!x.equals(null))
		{
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@GetMapping("/deleteUserBoardRole")
	@ResponseBody
	public ResponseEntity<Object> deleteUserBoardRole(@PathVariable("userRole")BoardUserRole bur, HttpServletRequest req)
	{
		if(services != null && bur != null && services.findOne(bur.getBoardId()) != null) 
		{
			services.delete(bur);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
}
