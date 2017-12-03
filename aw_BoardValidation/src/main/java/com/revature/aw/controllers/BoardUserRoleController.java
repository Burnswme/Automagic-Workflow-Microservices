package com.revature.aw.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aw.domain.BoardUserRole;
import com.revature.aw.services.BoardUserRoleServices;

@RestController
public class BoardUserRoleController 
{
	@Autowired
	private BoardUserRoleServices services;
	
	@GetMapping("/getUserBoards/{id}")
	@ResponseBody
	public ResponseEntity<List<BoardUserRole>> getUserBoards(@PathVariable("id") int userId) {
		return new ResponseEntity<>(services.findByUserId(userId), HttpStatus.OK);
	}
	
	@PostMapping("/createUserBoardRole")
	@ResponseBody
	public ResponseEntity<BoardUserRole> createUserBoardRole(@RequestBody BoardUserRole bur) {
		BoardUserRole x = services.save(bur);
		return (x != null) ? new ResponseEntity<>(x, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@GetMapping("/deleteUserBoardRole")
	@ResponseBody
	public ResponseEntity<Object> deleteUserBoardRole(@RequestBody BoardUserRole bur, HttpServletRequest req)
	{
		if(services != null && bur != null && services.findByUserIdAndBoardId(bur.getUserId(),bur.getBoardId()) != null) 
		{
			services.delete(bur);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@GetMapping("/fetchUserPrivileges/{user}/{board}")
	@ResponseBody
	public ResponseEntity<Boolean> determineUserPrivileges(@PathVariable("user") int userId, @PathVariable("board")int boardId) {
		return new ResponseEntity<>(services.determinePrivileges(userId, boardId), HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception> handleException(Exception e){
		return new ResponseEntity<Exception>(e,HttpStatus.CONFLICT);
	}
}
