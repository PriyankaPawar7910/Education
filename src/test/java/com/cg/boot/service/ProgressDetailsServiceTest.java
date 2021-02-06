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

import com.cg.boot.model.ProgressDetails;
import com.cg.boot.repository.ProgressDetailsRepository;
@SpringBootTest
class ProgressDetailsServiceTest {
	@Autowired
	IProgressDetailsService service;
	
	@MockBean
	ProgressDetailsRepository repository;
	ProgressDetailsService listMock = mock(ProgressDetailsService.class,"myMock");
	

	@Test
	public void getAllProgressDetailsTest() {
		when(repository.findAll()).thenReturn(Stream.of(new ProgressDetails(2, "A", "2021-02-06", 3, 4)).collect(Collectors.toList()));
		assertEquals(1,service.getAllProgressDetails().size());
		}
	
	@Test
	public void updateProgressDetailsTest() {
		ProgressDetails details=new ProgressDetails(2, "A", "2021-02-06", 3, 4);
		when(repository.save(details)).thenReturn(details);
		assertEquals(details, service.updateProgressDetails(details));
	}
	
	@Test
	
	public void deleteProgressDetailsTest() {
		ProgressDetails details= new ProgressDetails(2, "A", "2021-02-06", 3, 4);
		service.deleteProgressDetails(details.getGradeId(),details.getAdminId());
		verify(repository, times(1)).deleteById(details.getGradeId());
	}
	
	@Test
	public void addProgressDetailsTest() {
		ProgressDetails details= new ProgressDetails(2, "A", "2021-02-06", 3, 4);
		when(repository.save(details)).thenReturn(details);
		assertNotEquals(details,service.addProgressDetails(details));
	}
	
	/*@Test
	public void getAllProgressDetailsByStudentIdTest() {
		when(repository.findAllById(12)
	}*/
	
	@Test
	public void getAllProgressDetailsGradeTest() {
		when(repository.findAllByGrade("A")).thenReturn(Stream.of(new ProgressDetails(2, "A", "2021-02-06", 3, 4)).collect(Collectors.toList()));
		assertEquals(1,service.getAllProgressDetails("A").size());
	}
	@Test
	public void isValidDateTest() {
		when(listMock.isValidDate("2021-01-02")).thenReturn(true);
		boolean flag = listMock.isValidDate("2021-01-12");
		verify(listMock).isValidDate("2021-01-12");
		assertThat(flag);
	}

}

