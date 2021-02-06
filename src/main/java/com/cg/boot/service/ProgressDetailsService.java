package com.cg.boot.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.PreviousProgressDetails;
import com.cg.boot.model.ProgressDetails;
import com.cg.boot.repository.PreviousProgressDetailsRepository;
import com.cg.boot.repository.ProgressDetailsRepository;

@Service
@Transactional
public class ProgressDetailsService implements IProgressDetailsService {

	@Autowired
	ProgressDetailsRepository repository;
	@Autowired
	PreviousProgressDetailsRepository previousRepository;
	PreviousProgressDetails previousDetails;
	@Autowired
	UserService userService;
	/*
	 * Get all ProgressDetails By Grade
	 */

	@Override
	public List<ProgressDetails> getAllProgressDetails(String grade) {
		return repository.findAllByGrade(grade);
	}
/*
 * 
 */
	@Override
	public List<PreviousProgressDetails> getAllProgressDetailsByStudentId(int studentId) {
		userService.validateStudentId(studentId);
		return previousRepository.findAllByStudentId(studentId);
	}

	/*
	 * Add ProgressDetails
	 */
	@Override
	public ProgressDetails addProgressDetails(@Valid ProgressDetails progressDetails) {
		userService.validateAdminId(progressDetails.getAdminId());
    	userService.validateStudentId(progressDetails.getStudentId());
		previousDetails=new PreviousProgressDetails(progressDetails.getGrade(),progressDetails.getDate(),progressDetails.getAdminId(),progressDetails.getStudentId());
		previousRepository.save(previousDetails);
		return repository.save(progressDetails);
	}

	/*
	 * Update ProgressDetails
	 */
	@Override
	public ProgressDetails updateProgressDetails(ProgressDetails progressDetails) {
		userService.validateAdminId(progressDetails.getAdminId());
    	userService.validateStudentId(progressDetails.getStudentId());
    	if(!isValidDate(progressDetails.getDate())) {
    		throw new DataNotFoundException("Date should be in yyyy-MM-dd format");
    	}
		previousDetails=new PreviousProgressDetails(progressDetails.getGrade(),progressDetails.getDate(),progressDetails.getAdminId(),progressDetails.getStudentId());
		previousRepository.save(previousDetails);
		return repository.save(progressDetails);
	}

	/*
	 * Delete ProgressDetails
	 */
	@Override
	public List<ProgressDetails> deleteProgressDetails(int progressId,int userId) {
		userService.validateAdminId(userId);
		repository.deleteById(progressId);
		return repository.findAll();
	}
	/*
	 * get ProgressDetails By Id
	 */

	@Override
	public ProgressDetails getProgressDetails(int gradeId) {
		return repository.findById(gradeId).orElse(null);
	}

	/*
	 * Get All ProgressDetails
	 */
	@Override
	public List<ProgressDetails> getAllProgressDetails() {
		return repository.findAll();
	}
	
	@Override
    public boolean isValidDate(String date) {
		boolean flag = false;
		 String regex = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])"; 
		 Pattern p = Pattern.compile(regex); Matcher m = p.matcher(date);
		 if(m.matches()) {
			 flag = true;
		 }
		 return flag;
		
	}

}
