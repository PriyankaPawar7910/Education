package com.cg.boot.admin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.boot.model.User;
import com.cg.boot.service.ILoginService;

@RestController
@RequestMapping
public class LoginController {
	@Autowired
	ILoginService loginService;
	
	
	@GetMapping("/getUser/{id}/{password}")
	public String getUser(@PathVariable("id") int adminId,@PathVariable("password") String password){
		@SuppressWarnings("unused")
		User user = loginService.getAdminLogin(adminId,password);
		return "Login Successful";
		
	}
	

}

