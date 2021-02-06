package com.cg.boot.student.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.User;
import com.cg.boot.service.IUserService;

@RestController
@RequestMapping("/api")
public class StudentUserController {
	@Autowired
	IUserService userService;
	/*
	 * Add User
	 */

	@PostMapping("/addStudent")
	public User addUser(@Valid @RequestBody User userDetails) {
		User userInfo = userService.addUser(userDetails);
		return userInfo;
	}

	/*
	 * Get user based on id
	 */

	@GetMapping("/getStudent/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		User user = userService.getUser(id);
		if (user == null) {
			throw new DataNotFoundException("No user present with given id: " + id);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}
	/*
	 * Get all user
	 */

	@GetMapping("/getAllStudent")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userList = userService.getAllUsers();
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	/*
	 * Update User Details
	 */

	@PutMapping("/updateStudent")
	public ResponseEntity<User> updateUserDetails(@Valid @RequestBody User user) {
		User userInfo = userService.updateUserDetails(user);
		if (userInfo == null) {
			throw new DataNotFoundException("No user present to update");
		}
		return new ResponseEntity<User>(userInfo, HttpStatus.OK);
	}

	/*
	 * Delete User
	 */

	@DeleteMapping("/deleteStudent/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		List<User> user = userService.deleteUser(id);
		if (user == null) {
			throw new DataNotFoundException("No user present to delete with given id: " + id);
		}
		return "user id deleted successfully";
	}

}
