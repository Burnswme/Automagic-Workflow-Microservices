package com.revature.aw.auth.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.aw.auth.domain.BoardUser;

public interface BoardUserDao extends JpaRepository<BoardUser, Integer> {
	Optional<BoardUser> findByUsername(String username);
}
