
        package digital.department.RestApiControllers;

        import digital.department.moduls.Course;
        import digital.department.moduls.User;
        import digital.department.services.CoursesService;
        import digital.department.services.UserService;
        import lombok.RequiredArgsConstructor;
        import org.springframework.http.ResponseEntity;
        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Map;
@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/api/admin")
@RequiredArgsConstructor

public class AdminRestController {
    private final UserService userService;
    private final CoursesService coursesService;

    @GetMapping
    public ResponseEntity<Map<String, List<?>>> admin() {
        return ResponseEntity.ok(Map.of(
                "users", userService.list(),
                "courses", coursesService.list()
        ));
    }

    @PostMapping("/user/ban/{id}")
    public ResponseEntity<Void> userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user/edit/{userId}")
    public ResponseEntity<?> userEdit(@PathVariable("userId") Long userId,  @RequestBody User updatedUser) {
        User user = userService.getUserById(userId);
        user = updatedUser;
        userService.saveUser(user);
        return ResponseEntity.status(200).body("Изменения пользователя прошли успешно");
    }


    @PostMapping("/user-edit/course{courseId}/user{userId}")
    public ResponseEntity<?> addCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId) {
        User user = userService.getUserById(userId);
        Course course = coursesService.getCourseById(courseId);
        if (user == null || course == null) {
            return ResponseEntity.notFound().build();
        }
        user.setCourse(course);
        userService.saveUser(user);
        coursesService.addUserInCourse(user, course);
        return ResponseEntity.status(200).body("Курс успешно добавлен");
    }
    @GetMapping("/test")
    public String printMessage() {
        String message = "Hello, this is a REST endpoint!";
        System.out.println(message);
        return message;
    }
}