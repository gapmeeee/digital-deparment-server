package com.example.demo.repositoties;

import com.example.demo.moduls.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Lecture findByNumberOfLectures(int numberOfLecture);
}
