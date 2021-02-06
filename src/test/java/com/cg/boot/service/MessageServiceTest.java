package com.cg.boot.service;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.boot.model.Message;
import com.cg.boot.repository.MessageRepository;

@SpringBootTest
class MessageServiceTest {

	@Autowired
	IMessageService iMessageService;

	@MockBean
	MessageRepository repository;

	MessageService listMock = mock(MessageService.class, "myMock");

	@Test
	public void getAllMessagesTest() {
		when(repository.findAll())
				.thenReturn(Stream.of(new Message(103, "get ready", 1, 34, "2021-01-12")).collect(Collectors.toList()));
		assertNotNull(iMessageService.getAllMessages());
	}

	@Test
	public void getMessagesByStudentIdTest() {
		when(repository.findAllByStudentId(109))
				.thenReturn(Stream.of(new Message(103, "get ready", 1, 34, "2021-01-12")).collect(Collectors.toList()));
		assertEquals(1, iMessageService.getMessagesByStudentId(109).size());
	}

	@Test
	public void addMessagesTest() {
		Message message = new Message("Exams have postponed", 0, 108, "2021-01-23");
		when(repository.save(message)).thenReturn(message);
		assertNotEquals(message, iMessageService.addMessage(message));

	}

	@Test
	public void isValidMessageTest() {
		when(listMock.isValidMessage("Good luck")).thenReturn(true);
		boolean flag = listMock.isValidMessage("Hi");
		verify(listMock).isValidMessage("Hii");
		assertThat(flag);

	}

	@Test
	public void isValidDateTest() {
		when(listMock.isValidDate("2021-01-02")).thenReturn(true);
		boolean flag = listMock.isValidDate("2021-01-12");
		verify(listMock).isValidDate("2021-01-12");
		assertThat(flag);
	}

}
