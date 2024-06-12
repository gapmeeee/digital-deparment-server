package com.example.demo;


import com.example.demo.moduls.User;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String CreateUser(User user, Model model){
        if(!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с mail: " + user.getEmail() + "/n уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal){
        if (userService.getUserByPrincipal(principal).getEmail()==user.getEmail() ){
            model.addAttribute("user", user);
            return "user-info";
        }
        return "redirect:/";
    }

    @GetMapping("/hello")
    public String SecurityUrl(){
        return "hello";
    }
    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivate = userService.activateUser(code);
        if(isActivate){
            model.addAttribute("message", "Активация прошла успешно");
        }
        else {
            model.addAttribute("message", "Активация прошла не успешно");
        }
        return "login";
    }
}
