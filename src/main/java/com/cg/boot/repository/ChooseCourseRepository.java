package com.cg.boot.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.boot.model.ChooseCourse;

@Repository
public interface ChooseCourseRepository extends JpaRepository<ChooseCourse, Integer>{
	public List<ChooseCourse> findAllByStudentId(int studentId);
	public List<ChooseCourse> findAllByCourseName(String courseName);

}
