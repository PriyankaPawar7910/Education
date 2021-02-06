package com.cg.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.boot.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
