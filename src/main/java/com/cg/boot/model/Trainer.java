package com.cg.boot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "trainer_master")
public class Trainer {
	@Id
	@GeneratedValue
	@Column(name = "trainer_id")
	private Integer trainerId;

	@NotEmpty(message = "Name should not empty")
	@Size(min = 4, message = "min 4 chars required")
	@Column(name = "trainer_name")
	private String trainerName;

	@NotEmpty(message = "Phone number should not empty")
	@Size(min = 10, message = "min 10 digits required")
	@Column(name = "phone_no")
	private String phoneNo;

	@Email
	@NotEmpty(message = "Email should not empty")
	@Size(min = 12, message = "min 12 chars required")
	private String email;

	@NotEmpty(message = "Course name should Not be empty")
	private String courseName;

	@Column(name = "admin_id")
	@NotNull(message = "Admin Id should not null")
	private int adminId;

	public Trainer() {

	}

	public Trainer(Integer trainerId,
			@NotEmpty(message = "Name should not empty") @Size(min = 4, message = "min 4 chars required") String trainerName,
			@NotEmpty(message = "Phone number should not empty") @Size(min = 10, message = "min 10 digits required") String phoneNo,
			@Email @NotEmpty(message = "Email should not empty") @Size(min = 12, message = "min 12 chars required") String email,
			@NotEmpty(message = "Course name should Not be empty") String courseName,
			@NotNull(message = "Admin Id should not null") int adminId) {
		super();
		this.trainerId = trainerId;
		this.trainerName = trainerName;
		this.phoneNo = phoneNo;
		this.email = email;
		this.courseName = courseName;
		this.adminId = adminId;
	}

	public Trainer(
			@NotEmpty(message = "Name should not empty") @Size(min = 4, message = "min 4 chars required") String trainerName,
			@NotEmpty(message = "Phone number should not empty") @Size(min = 10, message = "min 10 digits required") String phoneNo,
			@Email @NotEmpty(message = "Email should not empty") @Size(min = 12, message = "min 12 chars required") String email,
			@NotEmpty(message = "Course name should Not be empty") String courseName,
			@NotNull(message = "Admin Id should not null") int adminId) {
		super();
		this.trainerName = trainerName;
		this.phoneNo = phoneNo;
		this.email = email;
		this.courseName = courseName;
		this.adminId = adminId;
	}

	public Integer getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(Integer trainerId) {
		this.trainerId = trainerId;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	@Override
	public String toString() {
		return "Trainer [trainerId=" + trainerId + ", trainerName=" + trainerName + ", phoneNo=" + phoneNo + ", email="
				+ email + ", courseName=" + courseName + ", adminId=" + adminId + "]";
	}

}
