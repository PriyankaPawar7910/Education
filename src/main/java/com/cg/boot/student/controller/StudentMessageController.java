package com.cg.boot.student.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.Message;
import com.cg.boot.service.IMessageService;


/**
 * @author Priyanka
 *
 */
@RestController
@RequestMapping("/api")
public class StudentMessageController {
	@Autowired
	IMessageService messageService;

	/**
	 * This method accepts and saves message which user has inserted through object.
	 * Return an object of message containing all arguments which has been saved.
	 * 
	 * @param messageDetails : {@link Message}
	 * @return messageInfo : {@link Message}
	 */
	@PostMapping("/addStudentMessage")
	public Message addMessage(@Valid @RequestBody Message messageDetails) {
		Message messageInfo = messageService.addMessage(messageDetails);
		return messageInfo;

	}
	/**
	 * This method accepts message Id which user has inserted. Return response
	 * entity containing message details based on message Id.
	 * 
	 * @param messageId : {@link Integer}
	 * @return {@link ResponseEntity} : message {@link Message}, {@link HttpStatus}
	 */
	@GetMapping("/getStudentMessage/{messageId}")
	public ResponseEntity<Message> getMessage(@PathVariable("messageId") int messageId) {
		Message message = messageService.getMessage(messageId);
		if (message == null) {
			throw new DataNotFoundException("No message present with given id: " + messageId);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);

	}

	/**
	 * This method returns list of all messages.
	 * 
	 * @return {@link ResponseEntity}: messageList {@link List}, {@link HttpStatus}
	 */
	@GetMapping("/getAllStudentMessages")
	public ResponseEntity<List<Message>> getAllMessages() {
		List<Message> messageList = messageService.getAllMessages();
		return new ResponseEntity<List<Message>>(messageList, HttpStatus.OK);

	}


	/**
	 * This method accepts student Id which user has inserted. Return response
	 * entity containing list of message details based on student Id.
	 * 
	 * @param studentId : {@link Integer}
	 * @return {@link ResponseEntity}: messageList {@link List}, {@link HttpStatus}
	 */
	@GetMapping("/getStudentMessagesByStudentId/{studentId}")
	public ResponseEntity<List<Message>> getMessagesByStudentId(@PathVariable("studentId") int studentId) {
		List<Message> message = messageService.getMessagesByStudentId(studentId);
		return new ResponseEntity<List<Message>>(message, HttpStatus.OK);
	}

}
