package com.cg.boot.service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.ChooseTrainer;
import com.cg.boot.model.Trainer;
import com.cg.boot.repository.ChooseTrainerRepository;
import com.cg.boot.repository.TrainerRepository;

@Service
@Transactional
public class TrainerService implements ITrainerService {
	@Autowired
	TrainerRepository repository;
	@Autowired
	ChooseTrainerRepository chooseTrainerRepository;
	@Autowired
	UserService userService;

	@Override
	public Trainer addTrainer(Trainer trainer) {
		userService.validateAdminId(trainer.getAdminId());
		if (trainer != null) {
			if (!isValidTrainerName(trainer.getTrainerName())) {
				throw new DataNotFoundException();
			} else if (!isValidTrainerPhoneNo(trainer.getPhoneNo())) {
				throw new DataNotFoundException();
			}
		}
		return repository.save(trainer);
	}

	@Override
	public Trainer updateTrainer(Trainer trainer) {
		userService.validateAdminId(trainer.getAdminId());
		Trainer updateTrainer = getTrainer(trainer.getTrainerId());
		if (updateTrainer != null) {
			if (!isValidTrainerName(trainer.getTrainerName())) {
				throw new DataNotFoundException();
			} else if (!isValidTrainerPhoneNo(trainer.getPhoneNo())) {
				throw new DataNotFoundException();
			}
			updateTrainer = repository.save(trainer);
		}
		return updateTrainer;
	}


	@Override
	public boolean isValidTrainerPhoneNo(String phoneNo) {
		boolean flag = false;
		String number = String.valueOf(phoneNo);
		String phoneregex = "^\\d{10}$";

		if (Pattern.matches(phoneregex, number)) {
			flag = true;
		} else {
			throw new DataNotFoundException("Invalid Phonenumber ");
		}
		return flag;
	}


	@Override
	public boolean isValidTrainerName(String trainerName) {
		boolean flag = false;
		String nameregex = "[A-Z][a-z]*";
		if (Pattern.matches(nameregex, trainerName)) {
			flag = true;
		} else {
			throw new DataNotFoundException("Given Trainer Name is Invalid ");
		}
		return flag;
	}

	@Override
	public Trainer getTrainer(int id) {
		return repository.findById(id).orElse(null);
	}
	@Override
	public ChooseTrainer getTrainerDetails(int trainerId,int studentId) {
		userService.validateStudentId(studentId);
		Trainer trainer= repository.findById(trainerId).orElse(null);
		ChooseTrainer chooseTrainer=null;
		if(trainer!=null) {
			chooseTrainer=new ChooseTrainer(studentId, trainerId, trainer.getTrainerName(),trainer.getCourseName());
		
		}
		chooseTrainerRepository.save(chooseTrainer);
		return chooseTrainer;
	}
	@Override
	public List<Trainer> getAllTrainers() {
		return repository.findAll();
	}

	@Override
	public List<Trainer> deleteTrainer(int trainerId,int userId) {
		userService.validateAdminId(userId);
		repository.deleteById(trainerId);
		return repository.findAll();
	}

}
