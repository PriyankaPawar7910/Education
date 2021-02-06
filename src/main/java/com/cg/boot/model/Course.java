package com.cg.boot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "course_master")
public class Course {
	@Id
	@GeneratedValue
	@Column(name = "course_id")
	private int courseId;

	@NotEmpty(message = "Name should not empty")
	@Size(min = 4, message = "min 4 chars required")
	@Column(name = "course_name")
	private String courseName;

	@NotNull(message = "Fee should not be empty")
	private Double fee;

	@Min(value = 18, message = "must be equal or greater than 18")
	@Max(value = 60, message = "must be equal or less than 45")
	private Integer duration;
	@Column(name = "admin_id")
	@NotNull(message = "Admin Id should not be null")
	private int adminId;

	public Course() {

	}

	public Course(int courseId,
			@NotEmpty(message = "Name should not empty") @Size(min = 4, message = "min 4 chars required") String courseName,
			@NotNull(message = "Fee should not be empty") Double fee,
			@Min(value = 18, message = "must be equal or greater than 18") @Max(value = 60, message = "must be equal or less than 45") Integer duration,
			@NotNull(message = "Admin Id should not be null") int adminId) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.fee = fee;
		this.duration = duration;
		this.adminId = adminId;
	}

	public Course(
			@NotEmpty(message = "Name should not empty") @Size(min = 4, message = "min 4 chars required") String courseName,
			@NotNull(message = "Fee should not be empty") Double fee,
			@Min(value = 18, message = "must be equal or greater than 18") @Max(value = 60, message = "must be equal or less than 45") Integer duration,
			@NotNull(message = "Admin Id should not be null") int adminId) {
		super();
		this.courseName = courseName;
		this.fee = fee;
		this.duration = duration;
		this.adminId = adminId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", fee=" + fee + ", duration=" + duration
				+ ", adminId=" + adminId + "]";
	}

}
