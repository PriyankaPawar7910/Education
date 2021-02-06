package com.cg.boot.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.Course;
import com.cg.boot.service.ICourseService;

@RestController
@RequestMapping
public class StudentCourseController {
	@Autowired
	ICourseService service;
	
	@GetMapping("/getStudentCourse/{id}")
	public ResponseEntity<Course> getCourse(@PathVariable("id") int id){

		Course getCourse = service.getCourse(id);
		if (getCourse == null) {
			throw new DataNotFoundException("No Course present with the given id ");
		}
		return new ResponseEntity<Course>(getCourse, HttpStatus.OK);
	}
	
	@GetMapping("/getAllStudentCourses")
	public ResponseEntity<List<Course>> getCourses() throws Exception {
		List<Course> getCourses = service.getAllCourses();
		return new ResponseEntity<List<Course>>(getCourses, HttpStatus.OK);
	}
	
	

}
