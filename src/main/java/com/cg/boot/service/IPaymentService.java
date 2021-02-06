package com.cg.boot.service;

import java.util.List;

import javax.validation.Valid;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.Payment;

public interface IPaymentService {

	Payment getPayment(int paymentId);

	List<Payment> getAllPayment();

	Payment addPayment(@Valid Payment payment) throws DataNotFoundException;

	Payment updatePayment(Payment payment,int userId) throws DataNotFoundException;

	List<Payment> deletePayment(int paymentId,int userId);

}
