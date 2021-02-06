package com.cg.boot.service;

import java.util.List;

import com.cg.boot.model.ChooseCourse;

public interface IChooseCourseService {
	ChooseCourse getChoosedCourseDetails(int courseId, int studentId);

	List<ChooseCourse> getChoosedCoursesByStudentId(int studentId);



}
