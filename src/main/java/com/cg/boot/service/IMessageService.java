package com.cg.boot.service;

import java.util.List;

import com.cg.boot.model.Message;

public interface IMessageService {

	public Message addMessage(Message message);

	public Message getMessage(int messageId);

	public List<Message> getAllMessages();

	public List<Message> getMessagesByStudentId(int studentId);

	public Message updateMessage(Message message);

	public List<Message> deleteMessage(int messageId, int userId);

	public boolean isValidMessage(String message);

	public boolean isValidDate(String date);

}
