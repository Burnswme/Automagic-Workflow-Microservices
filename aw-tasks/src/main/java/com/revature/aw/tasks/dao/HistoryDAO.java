package com.revature.aw.tasks.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.aw.tasks.domain.History;

@Repository
public interface HistoryDAO extends CrudRepository<History, Integer>{
	public History save(History hist);
}
