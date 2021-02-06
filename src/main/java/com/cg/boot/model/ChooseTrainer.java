package com.cg.boot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Student_Trainer")
public class ChooseTrainer {

	@Id
	@NotNull
	@Column(name = "student_Id")
	private int studentId;

	@NotNull
	@Column(name = "trainer_Id")
	private int trainerId;

	@NotEmpty
	@Column(name = "trainer_Name")
	private String trainerName;

	@NotEmpty
	@Column(name = "course_Name")
	private String courseName;

	ChooseTrainer() {

	}
 
	public ChooseTrainer(int studentId,int trainerId, String trainerName,String courseName) {
		super();
		this.studentId = studentId;
		this.trainerId = trainerId;
		this.trainerName = trainerName;
		this.courseName = courseName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Override
	public String toString() {
		return "ChooseTrainer [studentId=" + studentId + ", trainerId=" + trainerId + ", trainerName=" + trainerName
				+ ", courseName=" + courseName + "]";
	}

}
