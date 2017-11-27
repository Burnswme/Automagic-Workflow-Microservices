package com.revature.aw.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.aw.domain.History;

@Repository
public interface HistoryDAO extends CrudRepository<History, Integer>{
	public History save(History hist);
}
