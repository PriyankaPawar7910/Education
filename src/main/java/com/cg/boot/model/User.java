package com.cg.boot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@SuppressWarnings("serial")
@Entity
@Table(name = "user_master")
public class User implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private int userId;

	@NotEmpty(message = "First name should not be empty")
	@Column(name = "first_name")
	@Pattern(regexp = "[A-Z]{1}[a-z]*",message="First Name should be capital ")
	private String firstName;

	@NotEmpty(message = "last name should not be empty")
	@Pattern(regexp = "[A-Z]{1}[a-z]*", message = "Last Name should be capital")
	@Column(name = "last_name")
	private String lastName;

	@NotEmpty(message = "Email should not be empty")
	@Email
	private String email;

	@NotEmpty(message = "Password should not be empty")
	private String password;

	@NotNull(message = "Phone Number should not be empty")
	@Column(name = "phone_number")
	private long phoneNumber;

	@NotEmpty(message = "Address should not be empty")
	private String address;

	@NotEmpty(message = " Role type should not be empty")
	@Column(name = "role_type")
	private String roleType;

	public User() {

	}
	

	public User(int userId, @NotEmpty(message = "Password should not be empty") String password) {
		super();
		this.userId = userId;
		this.password = password;
	}


	public User(int userId, String firstName, String lastName, String email, String password, long phoneNumber,
			String address, String roleType) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.roleType = roleType;
	}

	public User(String firstName, String lastName, String email, String password, long phoneNumber, String address,
			String roleType) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.roleType = roleType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@Override
	public String toString() {
		return "Student [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", phoneNumber=" + phoneNumber + ", address=" + address + ", roleType="
				+ roleType + "]";
	}

}
