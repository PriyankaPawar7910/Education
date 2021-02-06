package com.cg.boot.service;

import java.util.List;

import javax.validation.Valid;

import com.cg.boot.model.PreviousProgressDetails;
import com.cg.boot.model.ProgressDetails;

public interface IProgressDetailsService {

	List<ProgressDetails> getAllProgressDetails(String grade);

	ProgressDetails addProgressDetails(@Valid ProgressDetails progressDetails);

	ProgressDetails updateProgressDetails(ProgressDetails progressDetails);

	List<ProgressDetails> deleteProgressDetails(int progressId,int userId);

	ProgressDetails getProgressDetails(int gradeId);

	List<ProgressDetails> getAllProgressDetails();

	List<PreviousProgressDetails> getAllProgressDetailsByStudentId(int studentId);

	boolean isValidDate(String date);

	

}
