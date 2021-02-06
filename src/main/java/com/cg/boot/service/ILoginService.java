package com.cg.boot.service;

import com.cg.boot.model.User;

public interface ILoginService {

	public User getAdminLogin(int adminId, String password);

	public User getStudentLogin(int studentId, String password);

}
