package bg.softuni.vikuslugivratsa.repository;

import bg.softuni.vikuslugivratsa.model.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
