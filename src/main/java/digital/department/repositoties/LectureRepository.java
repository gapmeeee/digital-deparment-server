package digital.department.repositoties;

import digital.department.moduls.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Lecture findByNumberOfLectures(int numberOfLecture);
}
