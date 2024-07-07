package digital.department.RestApiControllers;

import digital.department.moduls.Course;
import digital.department.moduls.File;
import digital.department.moduls.Lecture;
import digital.department.services.CoursesService;
import digital.department.services.FileService;
import digital.department.services.LectureService;
import digital.department.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseRestController {
    private final CoursesService coursesService;
    private final UserService userService;
    private final LectureService lectureService;
    private final FileService fileService;

    private final String uploadDir = "src/main/resources/Files/";


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

    @PostMapping("/lecture/{lectureId}/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                               @PathVariable("lectureId") Long lectureId) {
        try {

            Path filePath = Paths.get(uploadDir + file.getOriginalFilename());

            Files.write(filePath, file.getBytes());
            System.out.println(filePath);
            fileService.saveFile(file, lectureId, filePath);
            return ResponseEntity.ok("File uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("File upload failed.");

        }
    }
    @GetMapping("/download/{id}")
    public ResponseEntity<Map<String, byte[]>> downloadFile(@PathVariable Long id) {
        Lecture lecture = lectureService.getLectureById(id);
        List<File> files = lecture.getFiles();

        Map<String, byte[]> fileBytesMap = new HashMap<>();
        for (File file : files){
            Path filePath = Paths.get(file.getPath());
            try {
                byte[] fileBytes = Files.readAllBytes(filePath);
                fileBytesMap.put(file.getName(), fileBytes);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to read file");
            }

        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + lecture.getName() + "-files.zip\"")
                .body(fileBytesMap);
    }


}