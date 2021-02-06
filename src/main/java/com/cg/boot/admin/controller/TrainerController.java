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
import com.cg.boot.model.Trainer;
import com.cg.boot.service.ITrainerService;

@RestController
@RequestMapping("/api")
public class TrainerController {
	@Autowired
	ITrainerService service;

	@PostMapping("/addTrainer")
	public Trainer addTrainer(@Valid @RequestBody Trainer trainer) {
		Trainer addTrainer = service.addTrainer(trainer);
		return addTrainer;
	}

	@GetMapping("/getTrainer/{id}")
	public ResponseEntity<Trainer> getTrainer(@PathVariable("id") int id) {

		Trainer getTrainer = service.getTrainer(id);
		if (getTrainer == null) {
			throw new DataNotFoundException("No Trainer present with the given id: " + id);
		}
		return new ResponseEntity<Trainer>(getTrainer, HttpStatus.OK);
	}

	@GetMapping("/getAllTrainers")
	public ResponseEntity<List<Trainer>> getTrainers() throws Exception {
		List<Trainer> getTrainers = service.getAllTrainers();
		return new ResponseEntity<List<Trainer>>(getTrainers, HttpStatus.OK);
	}

	@PutMapping("/updateTrainer")
	public ResponseEntity<Trainer> updateTrainer(@Valid @RequestBody Trainer trainer) {
		Trainer updateTrainer = service.updateTrainer(trainer);
		return new ResponseEntity<Trainer>(updateTrainer, HttpStatus.OK);
	}

	@DeleteMapping("/deleteTrainer/{trainerId}/{userId}")
	public ResponseEntity<List<Trainer>> deleteTrainer(@PathVariable("trainerId") int trainerId,
			@PathVariable("userId") int userId) {
		List<Trainer> trainer = service.deleteTrainer(trainerId,userId);
		if (trainer == null) {
			throw new DataNotFoundException("No Trainer present with the given id: " + trainerId);
		}
		return new ResponseEntity<List<Trainer>>(trainer, HttpStatus.OK);
	}

}
