package com.example.demo;

import com.example.demo.moduls.Course;
import com.example.demo.moduls.Lecture;
import com.example.demo.services.CoursesService;
import com.example.demo.services.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LectureController {
    private LectureService lectureService;
    private CoursesService coursesService;


}
