package digital.department.repositoties;

import digital.department.moduls.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByPassword(String password);
    User findByActivationCode(String code);
}
