package com.example.demo.repositoties;

import com.example.demo.moduls.Course;
import com.example.demo.moduls.FAQ;
import com.example.demo.moduls.SupportTicket;
import com.example.demo.moduls.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FAQRepository extends JpaRepository<FAQ, Long> {
    FAQ findByThemes(String Theme);
}
