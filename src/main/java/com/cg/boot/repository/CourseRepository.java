package com.cg.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.boot.model.Course;

public interface CourseRepository extends JpaRepository<Course,Integer>{

	Course findByCourseName(String courseName);

}
