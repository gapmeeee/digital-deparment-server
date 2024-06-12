package com.example.demo.RestApiControllers;//package com.example.demo.restApiControllers;

import com.example.demo.moduls.SupportTicket;
import com.example.demo.services.SupportTicketService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/support-ticket")
public class SupportTicketController {
    private final SupportTicketService supportTicketService;
    private final UserService userService;

    @PostMapping("/create-message")
    public ResponseEntity<?> createMessage(@RequestBody SupportTicket supportTicket, Principal principal){
        userService.saveSupportTicket(supportTicket, principal);
        supportTicketService.saveSupportTicket(supportTicket, principal);

        return ResponseEntity.ok(supportTicket);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create-response")
    public ResponseEntity<?> createResponse(@RequestBody SupportTicket supportTicket){
        supportTicketService.saveResponse(supportTicket.getId(), supportTicket.getAdminResponse());
        return ResponseEntity.ok(supportTicket);
    }

    @GetMapping("/")
    public ResponseEntity<?> getSupportTicket(Principal principal){

        List<SupportTicket> supportTicket = supportTicketService.listUser(userService.getUserByPrincipal(principal).getId());
        return ResponseEntity.ok(supportTicket);
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getSupportTicketAdmin(){
        return ResponseEntity.ok(supportTicketService.listAdmin());
    }

}
