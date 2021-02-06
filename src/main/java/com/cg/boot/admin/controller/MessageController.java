package com.cg.boot.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
public class MessageController {
	@Autowired
	IMessageService messageService;

	/**
	 * This method accepts and saves message which user has inserted through object.
	 * Return an object of message containing all arguments which has been saved.
	 * 
	 * @param messageDetails : {@link Message}
	 * @return messageInfo : {@link Message}
	 */
	@PostMapping("/addMessage")
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
	@GetMapping("/getMessage/{messageId}")
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
	@GetMapping("/getAllMessages")
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
	@GetMapping("/getMessagesByStudentId/{studentId}")
	public ResponseEntity<List<Message>> getMessagesByStudentId(@PathVariable("studentId") int studentId) {
		List<Message> messageList = messageService.getMessagesByStudentId(studentId);
		return new ResponseEntity<List<Message>>(messageList, HttpStatus.OK);
	}

	/**
	 * This method accepts and update message which user has inserted through
	 * object. Return an object of message containing all arguments which has been
	 * updated.
	 * 
	 * @param message : {@link Message}
	 * @return messageInfo : {@link Message}
	 */
	@PutMapping("/updateMessage")
	public Message updateMessage(@RequestBody Message message) {
		Message messageInfo = messageService.updateMessage(message);
		return messageInfo;

	}

	/**
	 * This method accepts message Id to delete message based on message Id. Accepts
	 * user Id to check authorized user to perform operation. Return list of
	 * remaining messages except deleted one
	 * 
	 * @param messageId : {@link Integer}
	 * @param userId    : {@link Integer}
	 * @return {@link ResponseEntity}: messageList {@link List}, {@link HttpStatus}
	 */
	@DeleteMapping("/deleteMessage/{messageId}/{userId}")
	public ResponseEntity<List<Message>> deleteMessage(@PathVariable("messageId") int messageId,
			@PathVariable("userId") int userId) {
		List<Message> messageList = messageService.deleteMessage(messageId, userId);
		return new ResponseEntity<List<Message>>(messageList, HttpStatus.OK);
	}

}
