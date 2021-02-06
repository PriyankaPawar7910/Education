package com.cg.boot.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.boot.model.ProgressDetails;

public interface ProgressDetailsRepository extends JpaRepository<ProgressDetails,Integer>{
	public List<ProgressDetails> findAllByGrade(String grade);

}
