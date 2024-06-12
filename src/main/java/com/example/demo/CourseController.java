package com.example.demo;

import com.example.demo.moduls.Course;
import com.example.demo.moduls.Lecture;
import com.example.demo.services.CoursesService;
import com.example.demo.services.LectureService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CourseController {
    private final CoursesService coursesService;
    private final UserService userService;
    private final LectureService lectureService;

    @GetMapping("/courses")
    public String courses(@RequestParam(name="title", required = false) String title, Model model, Principal principal){
        model.addAttribute("courses", coursesService.listCourses(title));
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "courses";
    }

    @GetMapping("/courses/{id}")
    public String courseInfo(@PathVariable Long id, Model model){
        Course course = coursesService.getCourseById(id);
        model.addAttribute("course", course);
        model.addAttribute("lectures", course.getLectures());
        return "course-info";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/courses/create")
    public String createCourses(Course course) throws IOException{
        System.out.println(" Name of course  "+ course.getDescription());
        System.out.println(" Name of course  "+ course.getTitle());
        coursesService.saveCourse(course);
        return "redirect:/courses";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/courses/delete/{id}")
    public String deleteCourses(@PathVariable Long id) {
        coursesService.deleteCourse(id);
        return "redirect:/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/courses/{id}/lecture/create")
    public String createLecture(@PathVariable Long id, Lecture lecture){
        Course course = coursesService.getCourseById(id);
        lecture.setCourse(course);
        lectureService.saveLecture(lecture);
        System.out.println(" Name of lectures  "+ lecture.getName());
        System.out.println(" Name of Data  "+ lecture.getDateOfCreated());
        System.out.println(" Name of course  "+ lecture.getCourse().getTitle());
        //coursesService.addLectureInCourse(lecture, course);
        return "redirect:/courses";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/courses/{courseId}/lecture/delete/{lectureId}")
    public String deleteLecture(@PathVariable Long lectureId){
        lectureService.deleteLectures(lectureId);
        return "redirect:/courses";
    }
}
