package com.revature.aw.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.revature.aw.auth.dao.BoardUserDao;
import com.revature.aw.auth.domain.BoardUser;
import com.revature.aw.auth.domain.BoardUserDetails;

@Service
public class BoardUserService implements UserDetailsService {
	
	@Autowired
	private BoardUserDao dao;
	
	public BoardUser getUser(String username) throws UsernameNotFoundException {
		Optional<BoardUser> optUser = dao.findByUsername(username);
		optUser.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		return optUser.get();
	}
	/**
	 * Service method used to save a new User.
	 * @param user User to be saved.
	 * @return BoardUser that was saved/updated.
	 */
	public BoardUser saveUser(BoardUser user) {
		return dao.save(user);
	}
	
	@Override
	public BoardUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<BoardUser> optUser = dao.findByUsername(username);
		optUser.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		return optUser.map(BoardUserDetails::new).get();
	}
}