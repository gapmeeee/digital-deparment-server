package com.example.demo.services;


import com.example.demo.moduls.Course;
import com.example.demo.moduls.Lecture;
import com.example.demo.repositoties.CourseRepository;
import com.example.demo.repositoties.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LectureService {
    private final CourseRepository courseRepository;
    private final LectureRepository lectureRepository;

    public List<Lecture> listLectures(){ return lectureRepository.findAll();}

    public void saveLecture(Lecture lecture){
        lectureRepository.save(lecture);
    }
    public void deleteLectures(long id){lectureRepository.deleteById(id);}

    public Lecture getLectureById(Long id){return lectureRepository.findById(id).orElse(null);}
}
