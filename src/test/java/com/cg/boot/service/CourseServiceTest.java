package com.cg.boot.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.boot.model.Course;
import com.cg.boot.repository.CourseRepository;

@SpringBootTest
class CourseServiceTest {
	@Autowired
	ICourseService service;

	@MockBean
	CourseRepository repository;

	@Test
	public void getAllCourseTest() {
		when(repository.findAll())
				.thenReturn(Stream.of(new Course(21, "Java", 2500.0, 30, 6)).collect(Collectors.toList()));
		assertEquals(1, service.getAllCourses().size());
	}
	@Test
	public void addCourseTest() {
		Course course = new Course(21, "Java", 2500.0, 30, 6);
		when(repository.save(course)).thenReturn(course);
		assertNotEquals(course, service.addCourse(course));
	}

	@Test
	public void updateCourseTest() {
		Course course = new Course(21, "Java", 2500.0, 30, 6);
		when(repository.save(course)).thenReturn(course);
		assertEquals(course, service.updateCourse(course));
	}

	@Test
	public void deleteCourseTest() {
		Course course = new Course(21, "Java", 2500.0, 30, 6);
		service.deleteCourse(course.getCourseId(), course.getAdminId());
		verify(repository, times(1)).deleteById(course.getCourseId());

	}

}
