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
import com.cg.boot.model.ProgressDetails;
import com.cg.boot.service.IProgressDetailsService;

@RestController
@RequestMapping("/api")
public class StudentProgressDetailsController {

	@Autowired
	IProgressDetailsService service;
/*
 * Get All ProgressDetails By Grade
 */
	@GetMapping("/getStudentProgressDetails/{grade}")
	public ResponseEntity<List<ProgressDetails>> getGrade(@PathVariable String grade) throws DataNotFoundException {
		// List<Grade>list=null;
		List<ProgressDetails> grades = service.getAllProgressDetails(grade);
		if (grades.isEmpty()) {
			throw new DataNotFoundException("Progress Details Not Found");
		}
		return new ResponseEntity<List<ProgressDetails>>(grades, HttpStatus.OK);
	}
/*
 * Get ProgressDetails By Id
 */
	@GetMapping("/getStudentProgressDetailsById/{gradeId}")
	public ResponseEntity<ProgressDetails> getProgressDetails(@PathVariable("gradeId") int gradeId) throws DataNotFoundException {
		ProgressDetails grade = service.getProgressDetails(gradeId);
		if (grade == null) {
			throw new DataNotFoundException("Grade Not Found By these ID");
		}
		return new ResponseEntity<ProgressDetails>(grade, HttpStatus.OK);
	}
/*
 * Get All ProgressDetails
 */
	@GetMapping("/getAllStudentProgressDetails")
	public ResponseEntity<List<ProgressDetails>> getGrades() throws DataNotFoundException {
		List<ProgressDetails> grades = service.getAllProgressDetails();
		if (grades.isEmpty()) {
			throw new DataNotFoundException("Grades Details Not Found");
		}
		return new ResponseEntity<List<ProgressDetails>>(grades, HttpStatus.OK);
	}

}
