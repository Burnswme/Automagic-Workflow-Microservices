package com.revature.aw.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.revature.aw.auth.dao.LoginDao;
import com.revature.aw.auth.domain.Login;
import com.revature.aw.auth.domain.LoginDetails;

@Service
public class LoginService implements UserDetailsService {
	
	@Autowired
	private LoginDao dao;
	
	@Override
	public LoginDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Login> optUser = dao.findByUsername(username);
		optUser.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		return optUser.map(LoginDetails::new).get();
	}
	
	public Login saveLogin(Login user) {
		return dao.save(user);
	}
}