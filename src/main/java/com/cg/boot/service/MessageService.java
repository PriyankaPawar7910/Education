package com.cg.boot.service;

import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.Message;
import com.cg.boot.repository.MessageRepository;

/**
 * @author Priyanka
 *
 */
@Service
@Transactional
public class MessageService implements IMessageService {
	@Autowired
	MessageRepository repository;

	@Autowired
	UserService userService;

	/**
	 * This method saves message with passed message object. Check authorized user id to
	 * perform operation. Return a saved message.
	 * 
	 * @param message : {@link Message}
	 * @return {@link Message}
	 */
	@Override
	public Message addMessage(Message message) {
		if (userService.getUser(message.getCreatedByUserId()) == null) {
			throw new DataNotFoundException("Invalid User Id");
		}
		return repository.save(message);

	}

	/**
	 * This method finds message based on passed message Id. If message finds then
	 * return message or return null.
	 * 
	 * @param messageId : {@link Integer}
	 * @return {@link Message} or {@link Null}
	 */
	@Override
	public Message getMessage(int messageId) {
		return repository.findById(messageId).orElse(null);
	}

	/**
	 * This method finds all available messages. Return list of messages
	 * 
	 * @return {@link List}
	 */
	@Override
	public List<Message> getAllMessages() {
		return repository.findAll();
	}

	/**
	 * This method finds messages by passed student Id. Returns list of messages
	 * based on student Id. Check whether list of messages is empty or not.
	 * 
	 * @param studentId : {@link Integer}
	 * @return {@link List}
	 */
	@Override
	public List<Message> getMessagesByStudentId(int studentId) {
		List<Message> list = repository.findAllByStudentId(studentId);
		if (list.isEmpty()) {
			throw new DataNotFoundException("No messages are present with given student id: " + studentId);
		}
		return list;

	}

	/**
	 * This method update message with passed message object. Check input
	 * validations. Check authorized user to perform operation. Return updated
	 * message.
	 * 
	 * @param message : {@link Message}
	 * @return {@link Message}
	 */
	@SuppressWarnings("null")
	@Override
	public Message updateMessage(Message message) {
		userService.validateAdminId(message.getCreatedByUserId());

		if (!isValidMessage(message.getMessage())) {
			throw new DataNotFoundException("Message should has min 4 chars");
		}
		if (!isValidDate(message.getCreatedDate())) {
			throw new DataNotFoundException("Date should be in yyyy-MM-dd format");
		}
		if (getMessage(message.getMessageId()) == null) {
			throw new DataNotFoundException("No message present to update");
		}

		return repository.save(message);
	}

	/**
	 * This method performs deletion of message with passed message Id. Check
	 * authorize user to perform operation. Check message whether it is available to
	 * delete. Return list of all remaining messages except deleted one.
	 * 
	 * @param messageId : {@link Integer}
	 * @param userId    : {@link Integer}
	 * @return {@link List}
	 */
	@Override
	public List<Message> deleteMessage(int messageId, int userId) {
		userService.validateAdminId(userId);
		if (getMessage(messageId) == null) {
			throw new DataNotFoundException("No message present to delete with given id: " + messageId);
		}
		repository.deleteById(messageId);
		return repository.findAll();
	}

	/**
	 * This method perform validation of message. Check size of passed message.
	 * Return true or false based on condition.
	 * 
	 * @param message : {@link String}
	 * @return flag : {@link Boolean}
	 */
	@Override
	public boolean isValidMessage(String message) {
		boolean flag = false;
		if (message.length() > 4) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * This method performs validation of passed date. Check date format. Return
	 * true or false based on condition.
	 * 
	 * @param date : {@link String}
	 * @return flag : {@link Boolean}
	 */
	@Override
	public boolean isValidDate(String date) {
		boolean flag = false;
		String regex = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(date);
		if (m.matches()) {
			flag = true;
		}
		return flag;

	}

}
