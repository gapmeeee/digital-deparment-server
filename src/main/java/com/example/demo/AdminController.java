package com.example.demo;


import com.example.demo.moduls.Course;
import com.example.demo.moduls.User;
import com.example.demo.moduls.enums.Role;
import com.example.demo.services.CoursesService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final CoursesService coursesService;

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("users", userService.list());
        model.addAttribute("courses", coursesService.list());
        return "admin";
    }

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id){
        userService.banUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }
    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam("role") String role){
        userService.changeUserRoles(user, role);
        return "redirect:/admin";
    }
    @PostMapping("/admin/user/edit/courses/{user}")
    public String addCourse(@PathVariable("user") User user, @RequestParam("courseId") Course course){
        user.setCourse(course);
        userService.saveUser(user);
        coursesService.addUserInCourse(user,course);
        return "redirect:/admin";
    }
}
