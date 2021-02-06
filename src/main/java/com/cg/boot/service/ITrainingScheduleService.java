package com.cg.boot.service;

import java.util.List;

import com.cg.boot.model.TrainingSchedule;

public interface ITrainingScheduleService {

	public TrainingSchedule addSchedule(TrainingSchedule schedule);

	public TrainingSchedule getSchedule(int scheduleId);

	public List<TrainingSchedule> getAllSchedules();

	public List<TrainingSchedule> getScheduleByStudentId(int studentId);

	public TrainingSchedule updateSchedule(TrainingSchedule schedule);

	public List<TrainingSchedule> deleteSchedule(int scheduleId,int userId);

	public boolean isValidDate(String date);

}
