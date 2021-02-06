package com.cg.boot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.boot.model.Trainer;
import com.cg.boot.repository.TrainerRepository;

@SpringBootTest
public class TrainerServiceTest {

	@Autowired
	ITrainerService service;
	@MockBean
	TrainerRepository repository;

	TrainerService listMock = mock(TrainerService.class, "myMock");

	@Test
	public void getAllTrainersTest() {
		when(repository.findAll()).thenReturn(
				Stream.of(new Trainer(29, "Gari", "9863552556", "gari@gmail.com", "Successful Presentation", 6))
						.collect(Collectors.toList()));
		assertEquals(1, service.getAllTrainers().size());
	}

	@Test
	public void addTrainerTest() {
		Trainer trainer = new Trainer(30, "Sai", "8756453498", "sai@gmail.com", "TDD with JUnit 5", 6);
		when(repository.save(trainer)).thenReturn(trainer);
		assertEquals(trainer, service.addTrainer(trainer));
	}

	@Test
	public void deleteTrainer() {
		Trainer trainer = new Trainer(30, "Sai", "8756453498", "sai@gmail.com", "TDD with JUnit 5", 6);
		service.deleteTrainer(trainer.getTrainerId(), trainer.getAdminId());
		verify(repository, times(1)).deleteById(trainer.getTrainerId());
	}

	@Test
	public void updateTrainer() {
		Trainer trainer = new Trainer(30, "Sai Acuity", "8756483498", "sai@gmail.com", "TDD with JUnit 5", 6);
		when(repository.save(trainer)).thenReturn(trainer);
		assertNotEquals(trainer, service.updateTrainer(trainer));
	}

	@Test
	public void isValidTrainerPhoneNo() {
		when(listMock.isValidTrainerPhoneNo("9087675577")).thenReturn(true);
		boolean flag = listMock.isValidTrainerPhoneNo("9087675577");
		verify(listMock).isValidTrainerPhoneNo("9087675577");;
		assertThat(flag);
	}

	@Test
	public void isValidTrainerName() {
		when(listMock.isValidTrainerName("Sudheer")).thenReturn(true);
		boolean flag = listMock.isValidTrainerName("Sudheer");
		verify(listMock).isValidTrainerName("Sudheer");;
		assertThat(flag);
	}

}
