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
	
	/**
	 * Validation REST Endpoint to get all the boards a User can view/is attached to.
	 * @param userId The User whose Boards you want to view.
	 * @return A ResponseEntity object containing a list of BoardUserRoles, essentially a list of Boards the user is attached to.
	 */
	@GetMapping("/getUserBoards/{id}")
	@ResponseBody
	public ResponseEntity<List<BoardUserRole>> getUserBoards(@PathVariable("id") int userId) {
		return new ResponseEntity<>(services.findByUserId(userId), HttpStatus.OK);
	}
	
	/**
	 * Validation REST Endpoint to create a new BoardUserRole, essentially to add a new User to a Board.
	 * @param bur The appropriate BoardUserRole
	 * @return A ResponseEntity object with the corresponding BoardUserRole. Status OK if good, Status CONFLICT if bad.
	 */
	@PostMapping("/createUserBoardRole")
	@ResponseBody
	public ResponseEntity<BoardUserRole> createUserBoardRole(@RequestBody BoardUserRole bur) {
		BoardUserRole x = services.save(bur);
		return (x != null) ? new ResponseEntity<>(x, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	/**
	 * Validation REST Endpoint to delete a BoardUserRole. Supposed to remove a User from a Board.
	 * @param bur The User to be removed from a Board.
	 * @param req The HttpRequest
	 * @return Status OK if good, Status CONFLICT if bad.
	 */
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
	
	/**
	 * Validation REST Endpoint to check if a user is allowed to access a certain board.
	 * @param userId The User to be checked.
	 * @param boardId The Board to be checked upon.
	 * @return A ResponseEntity object with true/false attached depending on the result.
	 */
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
