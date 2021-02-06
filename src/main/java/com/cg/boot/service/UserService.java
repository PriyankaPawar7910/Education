package com.cg.boot.service;

import java.util.List;
import java.util.regex.Pattern;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.User;
import com.cg.boot.repository.UserRepository;

@Service
@Transactional
public class UserService implements IUserService {
	@Autowired
	UserRepository repository;

	/*
	 * Add Messages
	 */
     @Override
	public User addUser(User user) {
    	 if(!validatePhoneNumber(user.getPhoneNumber()))
    	 {
    		throw new DataNotFoundException("Invalid Phone Number") ;
    	 }
    	 if(!validateRoleType(user.getRoleType()))
    	 {
    		 throw new DataNotFoundException("Invalid Role Type");
    	 }
    	 if(! validatePassword(user.getPassword()))
    	 {
    		 throw new DataNotFoundException("Invalid Password");
    	 }
		return repository.save(user);
	}

	/*
	 * Get User based on id
	 */
	@Override
	public User getUser(int id) {
		return repository.findById(id).orElse(null);
	}

	/*
	 * Get All users
	 */

	@Override
	public List<User> getAllUsers() {
		return repository.findAll();

	}

	/*
	 * Update User details
	 */
	@Override
	public User updateUserDetails(@Valid User user) {
		User userDetails = getUser(user.getUserId());
		if (userDetails != null) {
			 if(!validatePhoneNumber(user.getPhoneNumber()))
	    	 {
	    		throw new DataNotFoundException("Invalid Phone Number") ;
	    	 }
	    	 if(!validateRoleType(user.getRoleType()))
	    	 {
	    		 throw new DataNotFoundException("Invalid Role Type");
	    	 }
	    	 if(! validatePassword(user.getPassword()))
	    	 {
	    		 throw new DataNotFoundException("Invalid Password");
	    	 }
			
			userDetails = repository.save(user);
		}
		return userDetails;
	}

	/*
	 * Delete user of given id
	 */

	@Override
	public List<User> deleteUser(int id) {
		List<User> list = null;
		User userDetails = getUser(id);
		if (userDetails != null) {
			repository.deleteById(id);
			list = getAllUsers();
		}
		return list;
	}
	/*
	 * Validate Phonenumber
	 */
	public boolean validatePhoneNumber(long phoneNumber) {
		boolean flag=false;
		String number=String.valueOf(phoneNumber);
		String regex="[7-9]{1}[0-9]{9}";
		if (Pattern.matches(regex,number)) {
			flag = true;
		}
			else {
				flag=false;
			}
		return flag;
	}
	
	/*
	 * Validate RoleType
	 */
	public boolean validateRoleType(String roleType) {
		boolean flag = false;

		if ((roleType.matches("admin"))|| (roleType.matches("student"))) {
			flag = true;
		} else {
			flag=false;
		}
		return flag;
	}
	
	/*
	 * Validate Password
	 */
	
	public boolean validatePassword(String password) {
		boolean flag=false;
		String passregex="^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
		if (Pattern.matches(passregex, password)) {
			flag = true;
		} else {
			flag=false;
		}
		return flag;
	}
	/*
	 * Valiadate Admin Id
	 */
	
	public boolean validateAdminId(int adminId) {
		User userInfo = getUser(adminId);
		if (userInfo == null) {
			throw new DataNotFoundException("Invalid user id");
		}
		if (!userInfo.getRoleType().equals("admin")) {
			throw new DataNotFoundException("You are not authorized");
		}
		return true;
	}
	/*
	 * Validate student Id
	 */
	public boolean validateStudentId(int studentId) {
		User userInfo = getUser(studentId);
		if(userInfo==null) {
			throw new DataNotFoundException("Invalid Student id");
		}
		if (!userInfo.getRoleType().equals("student")) {
			throw new DataNotFoundException("Student id is not valid");
		}
		return true;
	}

}
