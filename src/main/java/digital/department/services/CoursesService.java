package digital.department.services;

import digital.department.moduls.Course;
import digital.department.moduls.User;
import digital.department.moduls.Lecture;
import digital.department.repositoties.CourseRepository;
import digital.department.repositoties.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoursesService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public List<Course> listCourses(String title){
        if(title!=null) return courseRepository.findByTitle(title);
        return courseRepository.findAll();
    }

    public List<Course> list(){
        return courseRepository.findAll();
    }
    public void saveCourse(Course course)  throws IOException{
        Course CourseFromBd = courseRepository.save(course);

    }
    public void deleteCourse(long id){
        courseRepository.deleteById(id);
    }
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void addUserInCourse(User user, Course course){
        course.addUsersInList(user);
        courseRepository.save(course);
    }

    public void addLectureInCourse(Lecture lecture, Course course){
        course.addLecturesInList(lecture);
        courseRepository.save(course);
    }

}
