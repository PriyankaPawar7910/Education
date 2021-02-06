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
import com.cg.boot.model.ChooseCourse;
import com.cg.boot.service.ICourseService;

@RestController
@RequestMapping("/api")
public class ChooseCourseController {
	@Autowired
	ICourseService courseService;

	@GetMapping("/chooseCourse/{courseId}/{studentId}")
	public ResponseEntity<ChooseCourse> chooseCourse(@PathVariable("courseId") int courseId,
			@PathVariable("studentId") int studentId) {
		ChooseCourse course = courseService.getChoosedCourseDetails(courseId, studentId);
		if (course == null) {
			throw new DataNotFoundException("Course Details are not found");
		}
		return new ResponseEntity<ChooseCourse>(course, HttpStatus.OK);

	}

	@GetMapping("/getChoosedCoursesByStudentId/{studentId}")
	public ResponseEntity<List<ChooseCourse>> getChoosedCoursesByStudentId(@PathVariable("studentId") int studentId) {
		List<ChooseCourse> chooseCourses = courseService.getChoosedCoursesByStudentId(studentId);
		return new ResponseEntity<List<ChooseCourse>>(chooseCourses,HttpStatus.OK);
		
	}

}
