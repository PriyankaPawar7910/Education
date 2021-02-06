package com.cg.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.boot.model.ChooseTrainer;
@Repository
public interface ChooseTrainerRepository extends JpaRepository<ChooseTrainer, Integer>{

}
