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

import com.cg.boot.model.User;
import com.cg.boot.repository.UserRepository;
@SpringBootTest
class UserServiceTest {

	@Autowired
	IUserService service;
	
	@MockBean
	UserRepository repository;
	
	UserService listMock= mock(UserService.class,"myMock");
	
	@Test
	public void getAllUsersTest() {
		when(repository.findAll()).thenReturn( Stream.of(new User(2, "Kiran", "Sony", "kiran@gmail.com", "Kiran@123",
				9890851226l,"Jammu", "student")).collect(Collectors.toList()));
	    assertEquals(1, service.getAllUsers().size());
	}
	
	@Test
	public void updateUserDetailsTest() {
		User user=new User(23,"Samita","Gawande","samita@gmail.com", "Samita@123",7709920871l,"Nagpur","student");
		when(repository.save(user)).thenReturn(user);
		assertEquals(user, service.updateUserDetails(user));
	}
	
	@Test
	public void addUserDetailsTest() {
		User user= new User(23,"Tina","Khan","tinakhan@gmail.com","tina@123",8990090987l,"Chandrapur","student");
		when(repository.save(user)).thenReturn(user);
		assertNotNull(user);
	}
	
	@Test
	public void deleteUserDetailsTest() {
		User user= new User(23,"Tina","Khan","tinakhan@gmail.com","tina@123",8990090987l,"Chandrapur","student");
		service.deleteUser(user.getUserId());
		verify(repository, times(1)).deleteById(user.getUserId());
	}
	
	@Test
	public void validatePhoneNumber() {
		when(listMock.validatePhoneNumber(9890877612l)).thenReturn(true);
		boolean phoneFlag=listMock.validatePhoneNumber(9890851226l);
		verify(listMock).validatePhoneNumber(9890851226l);
		assertThat(phoneFlag);
	}
	
	@Test
	public void validateRoleTypeTest() {
		when(listMock.validateRoleType("admin")).thenReturn(false);
		boolean roleFlag=listMock.validateRoleType("admin");
		verify(listMock).validateRoleType("student");
		assertThat(roleFlag);
	}
	
	@Test
	public void validatePasswordTest() {
		when(listMock.validatePassword("Kavita@123 ")).thenReturn(false);
		boolean passFlag=listMock.validatePassword("Sarita@123");
		verify(listMock).validatePassword("Sarita@123");
		assertThat(passFlag);
	}
	
	@Test
	public void validateAdminIdTest() {
		when(listMock.validateAdminId(10)).thenReturn(true);
		boolean adminFlag=listMock.validateAdminId(10);
		verify(listMock).validateAdminId(9);
		assertThat(adminFlag);
		}
	
	@Test
	public void validateStudentIdTest() {
		when(listMock.validateStudentId(8)).thenReturn(true);
		boolean studentFlag=listMock.validateStudentId(8);
		verify(listMock).validateStudentId(8);
		assertThat(studentFlag);
	}
}
