package com.example.demo.services;


import com.example.demo.moduls.Image;
import com.example.demo.moduls.SupportTicket;
import com.example.demo.moduls.User;
import com.example.demo.enums.Role;
import com.example.demo.repositoties.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailSender;
    public boolean createUser(User user){
        String email = user.getEmail();
        if (userRepository.findByEmail(email)!= null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        user.setActivationCode(UUID.randomUUID().toString());
        log.info("Saving new User with Email: {}", email);
        userRepository.save(user);
        String message = String.format(
                "Hello, %s! \n" +
                        "Welcome to Digital Departments. Please, visit next link: http://localhost:8080/activate/%s",
                user.getUsername(),
                user.getActivationCode()
        );
        mailSender.send(user.getEmail(), "Activation code", message);
        return true;
    }
    public List<User> list(){
        return userRepository.findAll();
    }

    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user!=null){
            if(user.isActive()){
                user.setActive(false);
            }
            else {
                user.setActive(true);
            }
            log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
        }
        userRepository.save(user);

    }

    public void changeUserRoles(User user, String role) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        System.out.println("Attempting to change role to: " + role);
        user.getRoles().clear();
        if (roles.contains(role)) {
            user.getRoles().clear();  // Очищаем текущие роли пользователя
            user.getRoles().add(Role.valueOf(role));  // Добавляем новую роль
            System.out.println("Role changed to: " + role);
        }
        userRepository.save(user);
    }

    public void saveSupportTicket(SupportTicket supportTicket, Principal principal){
        User user = getUserByPrincipal(principal);
        user.addSupportTicket(supportTicket);
        userRepository.save(user);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }
    public User getUserByPrincipal(Principal principal) {
        if (principal==null){
            return new User();
        }
        return userRepository.findByEmail(principal.getName());
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if(user==null){
            return false;
        }
        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
    public void addImage(MultipartFile image, User user) throws IOException {
        Image avatar;
        if (image!=null){
            avatar = toImageEntity(image);
            user.setAvatar(avatar);
            userRepository.save(user);
        }
    }

    public Image getImage(User user) throws IOException {
        return user.getAvatar();
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(image.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
}
