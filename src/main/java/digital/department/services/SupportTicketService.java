package digital.department.services;

import digital.department.moduls.SupportTicket;
import digital.department.moduls.User;
import digital.department.repositoties.SupportTicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SupportTicketService {

    private final UserService  userService;
    private final SupportTicketRepository supportTicketRepository;

    public List<SupportTicket> listAdmin(){
        return supportTicketRepository.findAll();
    }

    public List<SupportTicket> listUser(Long id){
        User user = userService.getUserById(id);
        return supportTicketRepository.findByUser(user);
    }

    public SupportTicket FindBySubject(String subject){
        return supportTicketRepository.findBySubject(subject);
    }

    public void saveSupportTicket(SupportTicket supportTicket, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        supportTicket.setClosed(false);
        supportTicket.setAdminResponse(null);
        supportTicket.setUser(userService.getUserByPrincipal(principal));

        supportTicketRepository.save(supportTicket);
    }

    public void saveResponse(Long id, String answer){
        SupportTicket supportTicket = supportTicketRepository.findById(id).orElse(null);
        supportTicket.setAdminResponse(answer);
        User user = userService.getUserById(supportTicket.getUser().getId());
        supportTicket.setClosed(true);
        supportTicketRepository.save(supportTicket);
    }


}
