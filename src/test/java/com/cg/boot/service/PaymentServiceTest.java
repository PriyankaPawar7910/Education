package com.cg.boot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.boot.model.Payment;
import com.cg.boot.repository.PaymentRepository;

@SpringBootTest
class PaymentServiceTest {

	@Autowired
	IPaymentService service;
	
	@MockBean
	PaymentRepository repository;
	
	PaymentService listMock=mock(PaymentService.class,"my mock");
	
	@Test
	public void getAllPaymentTest() {
		when(repository.findAll()).thenReturn(Stream.of(new Payment(2, 2500.0, "credit",3456789067l, "successful",3))
				.collect(Collectors.toList()));
		assertNull(service.getAllPayment());
	}
	
	@Test
	public void addPaymentTest() {
	  Payment payment= new Payment(2, 2500.0, "credit",3456789067l, "successful",3);
	  when(repository.save(payment)).thenReturn(payment);
	  assertEquals(payment, service.addPayment(payment));
	}
	
	@Test
	public void updatePaymentTest() {
		Payment payment=new Payment(2, 2500.0, "credit",3456789067l, "successful",109);
		when(repository.save(payment)).thenReturn(payment);
		assertNotEquals(payment, service.updatePayment(payment,108));
	}
	
	@Test
	public void deletePaymentTest() {
		Payment payment=new Payment(2, 2500.0, "credit",3456789067l, "successful",108);
		service.deletePayment(payment.getStudentId(),108);
		verify(repository, times(1)).deleteById(payment.getPaymentId());
	}
	
	@Test
	public void isValidAccountNumberTest() {
		when(listMock.isvaliAccountNumber(234567890689l)).thenReturn(true);
		boolean  accountFlag=listMock.isvaliAccountNumber(234567890689l);
		verify(listMock).isvaliAccountNumber(234567890689l);
		assertThat(accountFlag);
	}
	
	@Test
	public void isvalidStatusTest() {
		when(listMock.isvalidStatus("sucessful")).thenReturn(true);
       boolean statusFlag= listMock.isvalidStatus("successful");
       verify(listMock).isvalidStatus("unsuccessful");
	  assertThat(statusFlag);
	}
	
	@Test
	public void  isvalidTypeTest() {
		when(listMock.isvalidStatus("credit")).thenReturn(true);
	       boolean typeFlag= listMock.isvalidType("credit");
	       verify(listMock).isvalidType("debit");
		  assertThat(typeFlag);	}

}
