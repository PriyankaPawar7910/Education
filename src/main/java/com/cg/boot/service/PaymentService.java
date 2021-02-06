package com.cg.boot.service;

import java.util.List;

import java.util.regex.Pattern;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.Message;
import com.cg.boot.model.Payment;
import com.cg.boot.repository.PaymentRepository;

/**
 * @author Nilima
 *
 */
@Service
@Transactional
public class PaymentService implements IPaymentService {

	@Autowired
	PaymentRepository repository;
	@Autowired
	UserService userService;

	/**
	 * This method finds payment based on passed payment Id. If payment finds then
	 * return payment or return null.
	 * 
	 * @param paymentId : {@link Integer}
	 * @return {@link Payment} or {@link Null}
	 */

	@Override
	public Payment getPayment(int paymentId) {
		return repository.findById(paymentId).orElse(null);
	}

	/**
	 * This method finds all available payments. Return list of payments
	 * 
	 * @return {@link List}
	 */

	@Override
	public List<Payment> getAllPayment() {
		return repository.findAll();
	}

	/**
	 * This method saves payment with passed payment object. Check authorized user
	 * id to perform operation. Check input validations. Return a saved payment
	 * 
	 * @param payment : {@link Payment}
	 * @return {@link Payment}
	 */
	@Override
	public Payment addPayment(Payment payment) throws DataNotFoundException {
		userService.validateStudentId(payment.getStudentId());

		if (!isvalidStatus(payment.getPaymentStatus())) {
			throw new DataNotFoundException("Enter valid Status Details");
		}
		if (!isvalidType(payment.getPaymentType())) {
			throw new DataNotFoundException("Enter valid Transaction Type Details either credit or debit");

		}
		if (!isvaliAccountNumber(payment.getAccountNo())) {
			throw new DataNotFoundException("Enter Valid Account Number");
		}
		return repository.save(payment);
	}

	/**
	 * This method perform validation of payment status. Check status whether
	 * successful or unsuccessful. Return true or false based on condition.
	 * 
	 * @param Status : {@link String}
	 * @return flag : {@link Boolean}
	 */
	public boolean isvalidStatus(String Status) {
		boolean flag = false;
		if (!(Status.equals("successful") || (Status.equals("unsuccessful")))) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	/**
	 * This method perform validation of payment type. Check payment type whether
	 * credit or debit. Return true or false based on condition.
	 * 
	 * @param Type : {@link String}
	 * @return flag : {@link Boolean}
	 */
	public boolean isvalidType(String Type) {
		boolean flag = false;
		if (!(Type.equals("credit") || (Type.equals("debit")))) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	/**
	 * This method perform validation of bank account number. Checks format of bank
	 * account number. Return true or false based on condition.
	 * 
	 * @param AccountNo : {@link Long}
	 * @return flag : {@link Boolean}
	 */
	public boolean isvaliAccountNumber(long AccountNo) throws DataNotFoundException {
		String bank = String.valueOf(AccountNo);
		String regex = "[0-9]{9,18}";
		boolean flag = false;
		if (Pattern.matches(regex, bank)) {
			flag = true;
		} else {
			throw new DataNotFoundException("Invalid Account Number");
		}

		return flag;
	}

	/**
	 * This method update payment with passed payment object. Check input
	 * validations. Check authorized user to perform operation. Return updated
	 * payment.
	 * 
	 * @param message : {@link Message}
	 * @return {@link Message}
	 */
	@Override
	public Payment updatePayment(Payment payment, int userId) throws DataNotFoundException {
		userService.validateAdminId(userId);
		userService.validateStudentId(payment.getStudentId());

		Payment paymentDetails = getPayment(payment.getPaymentId());

		if (paymentDetails != null) {

			if (!isvalidStatus(payment.getPaymentStatus())) {
				throw new DataNotFoundException("Enter valid Status Details");
			}

			if (!isvalidType(payment.getPaymentType())) {
				throw new DataNotFoundException("Enter valid Transaction Type Details either credit or debit");

			}
			if (!isvaliAccountNumber(payment.getAccountNo())) {
				throw new DataNotFoundException("Enter Valid Account Number");
			}
			paymentDetails = repository.save(payment);
		}
		return paymentDetails;
	}

	/**
	 * This method performs deletion of payment with passed payment Id. Check
	 * authorize user to perform operation. Return list of all remaining payments
	 * except deleted one.
	 * 
	 * @param paymentId : {@link Integer}
	 * @param userId    : {@link Integer}
	 * @return {@link List}
	 */
	@Override
	public List<Payment> deletePayment(int paymentId, int userId) {
		userService.validateAdminId(userId);
		repository.deleteById(paymentId);
		return repository.findAll();
	}

}
