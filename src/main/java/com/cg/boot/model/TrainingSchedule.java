package com.cg.boot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "training_schedule")
public class TrainingSchedule implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "schedule_id")
	private int scheduleId;
	@Column(name = "course_name")
	@NotEmpty(message = "Course Name should not empty")
	@Size(min = 4,message = "Min 4 chars required")
	private String courseName;
	@NotEmpty(message = "Date should not empty")
	@Pattern(regexp = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])", message = "Date should be in yyyy-MM-dd format")
	private String date;
	@NotNull(message = "Duration should not empty")
	private int duration;
	@Column(name = "student_id")
	@NotNull(message = "Student Id should not empty")
	private int studentId;
	@Column(name = "created_by_user_id")
	@NotNull(message = "Admin Id should not empty")
	private int createdByUserId;

	public TrainingSchedule() {

	}

	public TrainingSchedule(int scheduleId, @NotEmpty(message = "Course Name should not empty") String courseName,
			String date, @NotNull(message = "Duration should not empty") int duration,
			@NotNull(message = "Student Id should not empty") int studentId,
			@NotNull(message = "Admin Id should not empty") int createdByUserId) {
		super();
		this.scheduleId = scheduleId;
		this.courseName = courseName;
		this.date = date;
		this.duration = duration;
		this.studentId = studentId;
		this.createdByUserId = createdByUserId;
	}

	public TrainingSchedule(@NotEmpty(message = "Course Name should not empty") String courseName, String date,
			@NotNull(message = "Duration should not empty") int duration,
			@NotNull(message = "Student Id should not empty") int studentId,
			@NotNull(message = "Admin Id should not empty") int createdByUserId) {
		super();
		this.courseName = courseName;
		this.date = date;
		this.duration = duration;
		this.studentId = studentId;
		this.createdByUserId = createdByUserId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(int createdById) {
		this.createdByUserId = createdById;
	}

	@Override
	public String toString() {
		return "TrainingScheduleService [scheduleId=" + scheduleId + ", courseName=" + courseName + ", date=" + date
				+ ", duration=" + duration + ", studentId=" + studentId + ", createdByUserId=" + createdByUserId + "]";
	}

}
