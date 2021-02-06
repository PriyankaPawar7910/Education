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
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.TrainingSchedule;
import com.cg.boot.service.ITrainingScheduleService;

/**
 * @author Priyanka
 *
 */
@RestController
public class TrainingScheduleController {
	@Autowired
	ITrainingScheduleService scheduleService;

	/**
	 * This method accepts and saves Training Schedule which user has inserted
	 * through object. Return an object of Training Schedule containing all
	 * arguments which has been saved.
	 * 
	 * @param schedule : {@link TrainingSchedule}
	 * @return trainingSchedule : {@link TrainingSchedule}
	 */
	@PostMapping("/addSchedule")
	public TrainingSchedule addSchedule(@Valid @RequestBody TrainingSchedule schedule) {
		TrainingSchedule trainingSchedule = scheduleService.addSchedule(schedule);
		return trainingSchedule;

	}

	/**
	 * This method accepts schedule Id which user has inserted. Return response
	 * entity containing training schedule details based on schedule Id.
	 * 
	 * @param scheduleId : {@link Integer}
	 * @return {@link ResponseEntity}: trainingSchedule {@link TrainingSchedule},
	 *         {@link HttpStatus}
	 */
	@GetMapping("/getSchedule/{scheduleId}")
	public ResponseEntity<TrainingSchedule> getSchedule(@PathVariable("scheduleId") int scheduleId) {
		TrainingSchedule trainingSchedule = scheduleService.getSchedule(scheduleId);
		if (trainingSchedule == null) {
			throw new DataNotFoundException("No schedule present with given id: " + scheduleId);
		}
		return new ResponseEntity<TrainingSchedule>(trainingSchedule, HttpStatus.OK);

	}

	/**
	 * This method returns list of all training schedule
	 * 
	 * @return {@link ResponseEntity}: trainingSchedulesList {@link List},
	 *         {@link HttpStatus}
	 */
	@GetMapping("/getAllSchedules")
	public ResponseEntity<List<TrainingSchedule>> getAllSchedule() {
		List<TrainingSchedule> trainingSchedulesList = scheduleService.getAllSchedules();
		return new ResponseEntity<List<TrainingSchedule>>(trainingSchedulesList, HttpStatus.OK);

	}

	/**
	 * This method accepts student Id which user has inserted. Return response
	 * entity containing list of training schedule details based on student Id.
	 * 
	 * @param studentId : {@link Integer}
	 * @return {@link ResponseEntity}: trainingSchedulesList {@link List},
	 *         {@link HttpStatus}
	 */
	@GetMapping("/getScheduleByStudentId/{studentId}")
	public ResponseEntity<List<TrainingSchedule>> getScheduleByStudentId(@PathVariable("studentId") int studentId) {
		List<TrainingSchedule> trainingSchedulesList = scheduleService.getScheduleByStudentId(studentId);
		return new ResponseEntity<List<TrainingSchedule>>(trainingSchedulesList, HttpStatus.OK);
	}

	/**
	 * This method accepts and update training schedule which user has inserted
	 * through object. Return response entity containing details of training
	 * schedule which has been updated.
	 * 
	 * @param schedule : {@link TrainingSchedule}
	 * @return {@link ResponseEntity}: trainingSchedule {@link TrainingSchedule},
	 *         {@link HttpStatus}
	 */
	@PutMapping("/updateSchedule")
	public ResponseEntity<TrainingSchedule> updateSchedule(@Valid @RequestBody TrainingSchedule schedule) {
		TrainingSchedule trainingSchedule = scheduleService.updateSchedule(schedule);
		return new ResponseEntity<TrainingSchedule>(trainingSchedule, HttpStatus.OK);

	}

	/**
	 * This method accepts schedule Id to delete training schedule based on schedule
	 * Id. Accepts user Id to check authorized user to perform operation. Return
	 * list of remaining schedules except deleted one
	 * 
	 * @param scheduleId : {@link Integer}
	 * @param userId     : {@link Integer}
	 * @return {@link ResponseEntity}: trainingSchedulesList {@link List},
	 *         {@link HttpStatus}
	 */
	@DeleteMapping("/deleteSchedule/{scheduleId}/{userId}")
	public ResponseEntity<List<TrainingSchedule>> deleteSchedule(@PathVariable("scheduleId") int scheduleId,
			@PathVariable("userId") int userId) {
		List<TrainingSchedule> trainingSchedulesList = scheduleService.deleteSchedule(scheduleId, userId);
		return new ResponseEntity<List<TrainingSchedule>>(trainingSchedulesList, HttpStatus.OK);
	}

}
