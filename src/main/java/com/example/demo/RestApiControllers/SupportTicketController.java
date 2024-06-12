package com.example.demo.RestApiControllers;//package com.example.demo.restApiControllers;
//
//import com.example.demo.moduls.SupportTicket;
//import com.example.demo.services.SupportTicketService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/support-ticket")
//public class SupportTicketController {
//    SupportTicketService supportTicketService;
//    @PostMapping("/create-message")
//    public ResponseEntity<?> createMessage(@RequestBody SupportTicket supportTicket, Principal principal){
//        supportTicketService.saveSupportTicket(supportTicket, principal);
//        return ResponseEntity.ok(supportTicket);
//    }
//}
