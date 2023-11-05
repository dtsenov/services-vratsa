package bg.softuni.vikuslugivratsa.repository;

import bg.softuni.vikuslugivratsa.model.entity.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<WorkerEntity, Long> {
}
