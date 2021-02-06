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

import com.cg.boot.model.TrainingSchedule;
import com.cg.boot.repository.TrainingScheduleRepository;

@SpringBootTest
class TrainingScheduleServiceTest {
	@Autowired
	ITrainingScheduleService scheduleService;
	
	@MockBean
	TrainingScheduleRepository scheduleRepository;
	
	TrainingScheduleService listMock = mock(TrainingScheduleService.class,"myMock");
	
	@Test
	public void getAllScheduleTest() {
		when(scheduleRepository.findAll()).thenReturn(Stream.of(new TrainingSchedule(3, "Java", "2021-02-04", 30, 21, 108)).collect(Collectors.toList()));
		assertNotNull(scheduleService.getAllSchedules());
		
	}
	@Test
	public void getScheduleByStudentIdTest() {
		when(scheduleRepository.findAllByStudentId(109))
		.thenReturn(Stream.of(new TrainingSchedule(3, "Java", "2021-02-04", 30, 21, 108)).collect(Collectors.toList()));
		assertNotEquals(3, scheduleService.getAllSchedules().size());
	} 

	@Test
	public void addTrainingScheduleTest() {
		TrainingSchedule schedule= new TrainingSchedule(116, "Java", "2021-02-04", 30, 109, 108);
        when(scheduleRepository.save(schedule)).thenReturn(schedule);
		assertNull(schedule);
	}
	@Test
	public void isValidDateTest() {
		when(listMock.isValidDate("2021-01-02")).thenReturn(true);
		boolean flag = listMock.isValidDate("2021-01-12");
		verify(listMock).isValidDate("2021-01-12");
		assertThat(flag);
	}

}
