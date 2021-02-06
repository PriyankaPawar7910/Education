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
import com.cg.boot.model.PreviousProgressDetails;
import com.cg.boot.model.ProgressDetails;
import com.cg.boot.service.IProgressDetailsService;

@RestController
@RequestMapping("/api")
public class StudentPreviousProgressDetailsController {
	@Autowired
	IProgressDetailsService service;
/*
 * Get ProgressDetails By Student Id
 */
	@GetMapping("/getStudentPreviousProgressDetailsById/{studentId}")
	public ResponseEntity<List<PreviousProgressDetails>> getProgressDetails(@PathVariable("studentId") int studentId) throws DataNotFoundException {
		List<PreviousProgressDetails> list = service.getAllProgressDetailsByStudentId(studentId);
		if (list == null) {
			throw new DataNotFoundException("Student Grade Not Found By these ID");
		}
		return new ResponseEntity<List<PreviousProgressDetails>>(list,HttpStatus.OK);
	}
/*
 * Get All ProgressDetails
 */
	@GetMapping("/getStudentAllPreviousProgressDetails")
	public ResponseEntity<List<ProgressDetails>> getGrades() throws DataNotFoundException {
		List<ProgressDetails> grades = service.getAllProgressDetails();
		if (grades.isEmpty()) {
			throw new DataNotFoundException("Grades Details Not Found");
		}
		return new ResponseEntity<List<ProgressDetails>>(grades, HttpStatus.OK);
	}

}
