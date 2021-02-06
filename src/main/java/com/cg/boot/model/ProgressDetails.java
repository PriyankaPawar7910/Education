package com.cg.boot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Progress_master")
public class ProgressDetails {

	@Id
	@GeneratedValue
	@Column(name = "grade_Id")
	private int gradeId;

	@NotEmpty(message = "Grade should Not Empty")
	@Pattern(regexp = "[A-Z]{1}+[+]*", message = "length must be 1 or 2")
	private String grade;
	@NotEmpty(message = "Date should not empty")
	@Pattern(regexp = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])", message = "Date should be in yyyy-MM-dd format")
	private String date;

	@NotNull(message = "Student Id Should not be blank")
	@Min(value = 1, message = "Enter valid student ID")
	@Column(name = "student_Id")
	private int studentId;

	@NotNull(message = "Admin Id Should not be blank")
	@Column(name = "admin_Id")
	@Min(value = 1, message = "Enter valid admin ID")
	private int adminId;

	public ProgressDetails() {

	}

	public ProgressDetails(int gradeId, String grade, String date, int studentId, int adminId) {
		super();
		this.gradeId = gradeId;
		this.grade = grade;
		this.date = date;
		this.studentId = studentId;
		this.adminId = adminId;
	}

	public ProgressDetails(String grade, String date, int studentId, int adminId) {
		super();
		this.grade = grade;
		this.date = date;
		this.studentId = studentId;
		this.adminId = adminId;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	@Override
	public String toString() {
		return "Grade [gradeId=" + gradeId + ", grade=" + grade + ", date=" + date + ", studentId=" + studentId
				+ ", adminId=" + adminId + "]";
	}

}
