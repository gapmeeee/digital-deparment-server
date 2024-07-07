package digital.department.repositoties;

import digital.department.moduls.File;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FileRepository extends JpaRepository<File, Long> {
}
