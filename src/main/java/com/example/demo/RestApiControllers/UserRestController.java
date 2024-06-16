package com.example.demo.RestApiControllers;

import com.example.demo.moduls.User;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Base64;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserRestController {
    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (!userService.createUser(user)) {
            return ResponseEntity.badRequest().body("Пользователь с почтой: " + user.getEmail() + " уже существует");
        }
        String credentials = user.getEmail() + ":" + user.getPassword();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

        return ResponseEntity.ok("Регистрация прошла успешно");
    }

    @GetMapping("/user/{user}")
    public ResponseEntity<?> userInfo(@PathVariable("user") User user, Principal principal) {
        if (userService.getUserByPrincipal(principal).getEmail().equals(user.getEmail())) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(403).body("Доступ запрещен");
    }
    @GetMapping("/admin/user/{user}")
    public ResponseEntity<?> userInfoAdmin(@PathVariable("user") Long id) {
            return ResponseEntity.ok(userService.getUserById(id));

    }
    @GetMapping("/login")
    public ResponseEntity<?> login(Principal principal) {
        if(principal==null){
            return ResponseEntity.status(401).body("Пользователь не найден");
        }
        User user = userService.getUserByPrincipal(principal);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/avatar")
    public ResponseEntity<?> addImage(@RequestParam("image") MultipartFile image, Principal principal)  throws IOException {
        User user = userService.getUserByPrincipal(principal);
        if (user == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        userService.addImage(image, user);
        return ResponseEntity.ok("Image uploaded successfully");
    }
    @GetMapping("/get-avatar")
    public ResponseEntity<byte[]> getImage(Principal principal)  throws IOException {
        User user = userService.getUserByPrincipal(principal);
//        if (user!= null && user.getAvatar()!=null) {
            return ResponseEntity.ok(user.getAvatar().getBytes());

    }

}