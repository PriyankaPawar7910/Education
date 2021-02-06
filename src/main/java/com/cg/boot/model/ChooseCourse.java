package com.cg.boot.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="student_course")
public class ChooseCourse {
    @Id
    @GeneratedValue
    private int id;
	@Column(name="student_id")
	@NotNull(message = "Student id should not null")
	private int studentId;
	@Column(name = "course_id")
	@NotNull(message = "Course id should not null")
	private int courseId;
	@Column(name = "course_name")
	@NotEmpty(message = "Course Name should not empty")
	private String courseName;
	
	public ChooseCourse() {
		
	}

	public ChooseCourse(@NotNull(message = "Student id should not null") int studentId,
			@NotNull(message = "Course id should not null") int courseId,
			@NotEmpty(message = "Course Name should not empty") String courseName) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
		this.courseName = courseName;
	}
	

	public ChooseCourse(int id, @NotNull(message = "Student id should not null") int studentId,
			@NotNull(message = "Course id should not null") int courseId,
			@NotEmpty(message = "Course Name should not empty") String courseName) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.courseId = courseId;
		this.courseName = courseName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
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
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ChooseCourse [id=" + id + ", studentId=" + studentId + ", courseId=" + courseId + ", courseName="
				+ courseName + "]";
	}

	
	

}
