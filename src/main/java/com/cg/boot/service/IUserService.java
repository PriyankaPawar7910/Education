package com.cg.boot.service;

import java.util.List;

import javax.validation.Valid;

import com.cg.boot.model.User;

public interface IUserService {

	User addUser(User user);

	User getUser(int id);

	List<User> getAllUsers();

	User updateUserDetails(@Valid User user);

	List<User> deleteUser(int id);

}
