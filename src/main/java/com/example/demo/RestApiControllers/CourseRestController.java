package com.example.demo.RestApiControllers;

import com.example.demo.moduls.Course;
import com.example.demo.moduls.Lecture;
import com.example.demo.services.CoursesService;
import com.example.demo.services.LectureService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController

@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseRestController {
    private final CoursesService coursesService;
    private final UserService userService;
    private final LectureService lectureService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<Course>> getCourses(@RequestParam(name = "title", required = false) String title) {
        List<Course> courses = coursesService.listCourses(title);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/")
    public ResponseEntity<?> getCourseInfo(Principal principal) {
        Course course = userService.getUserByPrincipal(principal).getCourse();
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create/test")
    public ResponseEntity<?> test(@RequestBody Course course) throws IOException {
        coursesService.saveCourse(course);
        return ResponseEntity.ok(course);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody Course course) throws IOException {
        coursesService.saveCourse(course);
        return ResponseEntity.ok(course);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        coursesService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{id}/lecture/create")
    public ResponseEntity<Lecture> createLecture(@PathVariable Long id, @RequestBody Lecture lecture) {
        Course course = coursesService.getCourseById(id);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        lecture.setCourse(course);
        lectureService.saveLecture(lecture);
        return ResponseEntity.ok(lecture);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{courseId}/lecture/delete/{lectureId}")
    public ResponseEntity<Void> deleteLecture(@PathVariable Long lectureId) {
        lectureService.deleteLectures(lectureId);
        return ResponseEntity.noContent().build();
    }
}