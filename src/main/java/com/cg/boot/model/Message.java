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
@Table(name = "message_master")
public class Message implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "message_id")
	private int messageId;
	@NotEmpty(message = "Message should not be empty")
	@Size(min = 4, message = "Minimum 4 chars required")
	private String message;
	@Column(name = "student_id")
	@NotNull(message = "Student Id should not be empty")
	private int studentId;
	@Column(name = "created_by_user_id")
	@NotNull(message = "User Id should not be empty")
	private int createdByUserId;
	@Column(name = "created_date")
	@NotEmpty(message = "Date should not be empty")
	@Pattern(regexp = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])", message = "Date should be in yyyy-MM-dd format")
	private String createdDate;

	public Message() {

	}

	public Message(int messageId, String message, int studentId, int createdByUserId, String createdDate) {
		super();
		this.messageId = messageId;
		this.message = message;
		this.studentId = studentId;
		this.createdByUserId = createdByUserId;
		this.createdDate = createdDate;
	}

	public Message(String message, int studentId, int createdByUserId, String createdDate) {
		super();
		this.message = message;
		this.studentId = studentId;
		this.createdByUserId = createdByUserId;
		this.createdDate = createdDate;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(int createdById) {
		this.createdByUserId = createdById;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", message=" + message + ", studentId=" + studentId
				+ ", createdByUserId=" + createdByUserId + ", createdDate=" + createdDate + "]";
	}

}
