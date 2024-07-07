package digital.department.services;

import digital.department.moduls.File;
import digital.department.moduls.Lecture;

import digital.department.repositoties.FileRepository;
import digital.department.repositoties.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final LectureRepository lectureRepository;

    public void saveFile(MultipartFile file, Long lectureId, Path filePath) throws IOException {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lecture ID"));
        File fileEntity = new File();
        fileEntity.setName(file.getOriginalFilename());
        fileEntity.setContentType(file.getContentType());
        fileEntity.setSize(file.getSize());
        fileEntity.setPath(filePath.toString());
        fileEntity.setLecture(lecture);

        fileRepository.save(fileEntity);
    }

    public File findById(Long id) {
        return fileRepository.findById(id).orElse(null);
    }
}