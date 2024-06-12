package com.example.demo;

import com.example.demo.repositoties.UserRepository;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/employee")
public class Сontroller {
    private final UserService userService;
    @GetMapping("/")
    public String products(@RequestParam(name="title", required = false) String title, Principal principal, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "main";
    }



}
