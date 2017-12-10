package com.revature.aw.auth.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.aw.auth.domain.Login;

public interface LoginDao extends JpaRepository<Login, Integer> {
	Optional<Login> findByUsername(String username);
}
