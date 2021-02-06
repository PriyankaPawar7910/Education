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
import com.cg.boot.model.ProgressDetails;
import com.cg.boot.service.IProgressDetailsService;

@RestController
@RequestMapping("/api")
public class ProgressDetailsController {

	@Autowired
	IProgressDetailsService service;

	/*
	 * Get All ProgressDetails By Grade
	 */
	@GetMapping("/getProgressDetails/{grade}")
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
	@GetMapping("/getProgressDetailsById/{gradeId}")
	public ResponseEntity<ProgressDetails> getProgressDetails(@PathVariable("gradeId") int gradeId)
			throws DataNotFoundException {
		ProgressDetails grade = service.getProgressDetails(gradeId);
		if (grade == null) {
			throw new DataNotFoundException("Grade Not Found By these ID");
		}
		return new ResponseEntity<ProgressDetails>(grade, HttpStatus.OK);
	}

	/*
	 * Get All ProgressDetails
	 */
	@GetMapping("/getAllProgressDetails")
	public ResponseEntity<List<ProgressDetails>> getGrades() throws DataNotFoundException {
		List<ProgressDetails> grades = service.getAllProgressDetails();
		if (grades.isEmpty()) {
			throw new DataNotFoundException("Grades Details Not Found");
		}
		return new ResponseEntity<List<ProgressDetails>>(grades, HttpStatus.OK);
	}

	/*
	 * Add ProgressDetails
	 */
	@PostMapping("/addProgressDetails")
	public ResponseEntity<Integer> addProgressDetails(@Valid @RequestBody ProgressDetails progressDetails) {
		ProgressDetails gradeInfo = service.addProgressDetails(progressDetails);

		return new ResponseEntity<Integer>(gradeInfo.getGradeId(), HttpStatus.OK);
	}

	/*
	 * Update ProgressDetails
	 */
	@PutMapping("/updateGrade")
	public ResponseEntity<ProgressDetails> updateProgressDetails(@RequestBody ProgressDetails progressDetails)
			throws DataNotFoundException {
		ProgressDetails gradeInfo = service.updateProgressDetails(progressDetails);
		if (gradeInfo == null) {
			throw new DataNotFoundException("Grade Details Not Found for update");
		}
		return new ResponseEntity<ProgressDetails>(gradeInfo, HttpStatus.OK);
	}

	/*
	 * Delete ProgressDetails
	 */
	@DeleteMapping("/deleteProgressDetails/{progressId}/{userId}")
	public ResponseEntity<List<ProgressDetails>> deleteProgressDetails(@PathVariable("progressId") int progressId,
			@PathVariable("userId") int userId) {
		List<ProgressDetails> grades = service.deleteProgressDetails(progressId,userId);
		return new ResponseEntity<List<ProgressDetails>>(grades, HttpStatus.OK);
	}

}
