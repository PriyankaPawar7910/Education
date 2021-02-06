package com.cg.boot.service;

import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.TrainingSchedule;
import com.cg.boot.repository.TrainingScheduleRepository;

/**
 * 
 * @author Priyanka
 *
 */

@Service
@Transactional
public class TrainingScheduleService implements ITrainingScheduleService {
	@Autowired
	TrainingScheduleRepository repository;
	@Autowired
	UserService userService;
	@Autowired
	CourseService courseService;

	/**
	 * This method saves training schedule with passed schedule object. check input
	 * validation Return saved training schedule.
	 * 
	 * @param schedule : {@link TrainingSchedule}
	 * @return trainingSchedule : {@link TrainingSchedule}
	 */
	@Override
	public TrainingSchedule addSchedule(TrainingSchedule schedule) {
		userService.validateAdminId(schedule.getCreatedByUserId());
		userService.validateStudentId(schedule.getStudentId());
		courseService.validateCourse(schedule.getCourseName());
		TrainingSchedule trainingSchedule = repository.save(schedule);
		return trainingSchedule;
	}

	/**
	 * This method finds training schedule based on passed schedule Id. If schedule
	 * finds then return schedule or return null.
	 * 
	 * @param scheduleId : {@link Integer}
	 * @return {@link TrainingSchedule} or {@link Null}
	 */
	@Override
	public TrainingSchedule getSchedule(int scheduleId) {
		return repository.findById(scheduleId).orElse(null);
	}

	/**
	 * This method finds all available training schedule. Return list of schedule
	 * 
	 * @return {@link List}
	 */
	@Override
	public List<TrainingSchedule> getAllSchedules() {
		return repository.findAll();
	}

	/**
	 * This method finds training schedule by passed student Id. Returns list of
	 * schedules based on student Id. Check whether list of schedules is empty or
	 * not.
	 * 
	 * @param studentId : {@link Integer}
	 * @return {@link List}
	 */

	@Override
	public List<TrainingSchedule> getScheduleByStudentId(int studentId) {
		List<TrainingSchedule> scheduleList = repository.findAllByStudentId(studentId);
		if (scheduleList.isEmpty()) {
			throw new DataNotFoundException("No schedules are present with given student id: " + studentId);
		}
		return scheduleList;
	}

	/**
	 * This method update training schedule with passed schedule object. Check input
	 * validations. Check authorized user to perform operation. Return updated
	 * schedule.
	 * 
	 * @param schedule : {@link TrainingSchedule}
	 * @return {@link TrainingSchedule}
	 */
	@Override
	public TrainingSchedule updateSchedule(TrainingSchedule schedule) {
		userService.validateAdminId(schedule.getCreatedByUserId());
		userService.validateStudentId(schedule.getStudentId());
		courseService.validateCourse(schedule.getCourseName());
		if (!isValidDate(schedule.getDate())) {
			throw new DataNotFoundException("Date should be in yyyy-MM-dd format");
		}
		if (getSchedule(schedule.getScheduleId()) == null) {
			throw new DataNotFoundException("No schedule present to update");
		}
		return repository.save(schedule);
	}

	/**
	 * This method performs deletion of training schedule with passed schedule Id.
	 * Check authorize user to perform operation. Check schedule whether it is
	 * available to delete. Return list of all remaining schedules except deleted
	 * one.
	 * 
	 * @param scheduleId : {@link Integer}
	 * @param userId     : {@link Integer}
	 * @return {@link List}
	 */
	@Override
	public List<TrainingSchedule> deleteSchedule(int scheduleId, int userId) {
		userService.validateAdminId(userId);
		if (getSchedule(scheduleId) == null) {
			throw new DataNotFoundException("No schedule present to delete with given id: " + scheduleId);
		}
		repository.deleteById(scheduleId);
		return repository.findAll();
	}

	/**
	 * This method perform validation of date. Check format of passed date. Return
	 * true or false based on condition.
	 * 
	 * @param date : {@link String}
	 * @return flag : {@link Boolean}
	 */

	@Override
	public boolean isValidDate(String date) {
		boolean flag = false;
		String regex = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(date);
		if (m.matches()) {
			flag = true;
		}
		return flag;

	}

}
