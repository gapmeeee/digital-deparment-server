package com.example.demo.repositoties;

import com.example.demo.moduls.SupportTicket;
import com.example.demo.moduls.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {
    SupportTicket findBySubject(String subject);

    List<SupportTicket> findByUser(User user);
}
