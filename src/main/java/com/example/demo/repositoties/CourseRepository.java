package com.example.demo.repositoties;

import com.example.demo.moduls.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository  extends JpaRepository<Course, Long>{
    List<Course> findByTitle(String title);

}
