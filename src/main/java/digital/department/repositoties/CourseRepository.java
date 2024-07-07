package digital.department.repositoties;

import digital.department.moduls.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository  extends JpaRepository<Course, Long>{
    List<Course> findByTitle(String title);

}
