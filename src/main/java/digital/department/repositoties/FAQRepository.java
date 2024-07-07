package digital.department.repositoties;

import digital.department.moduls.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FAQRepository extends JpaRepository<FAQ, Long> {
    FAQ findByThemes(String Theme);
}
