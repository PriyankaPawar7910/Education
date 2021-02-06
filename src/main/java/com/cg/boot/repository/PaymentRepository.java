package com.cg.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.boot.model.Payment;


public interface PaymentRepository extends JpaRepository<Payment,Integer>{

}
