package com.cg.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.boot.model.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

}
