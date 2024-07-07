package digital.department.services;


import digital.department.moduls.Lecture;
import digital.department.repositoties.CourseRepository;
import digital.department.repositoties.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
