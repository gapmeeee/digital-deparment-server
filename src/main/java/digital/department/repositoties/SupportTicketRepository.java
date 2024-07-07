package digital.department.repositoties;

import digital.department.moduls.SupportTicket;
import digital.department.moduls.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {
    SupportTicket findBySubject(String subject);

    List<SupportTicket> findByUser(User user);
}
