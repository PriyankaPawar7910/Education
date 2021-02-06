package com.cg.boot.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.boot.model.User;
import com.cg.boot.repository.UserRepository;
@SpringBootTest
class LoginServiceTest {

	@Autowired
	ILoginService service;
	
	@MockBean
	UserRepository repository;
	
	LoginService listMock=mock(LoginService.class,"myMock");
	
	@Test
	public void getAdminLoginTest() {
		User user= new User(8,"Ishar@123");
		when(listMock.getAdminLogin(8, "Ishar@123")).thenReturn(user);
		assertEquals(user, listMock.getAdminLogin(8, "Ishar@123"));
	}
	@Test
	public void getStudentLoginTest() {
		User user= new User(8,"Ishar@123");
		when(listMock.getStudentLogin(8, "Ishar@123")).thenReturn(user);
		assertEquals(user, listMock.getStudentLogin(8, "Ishar@123"));
	}
	
	

}