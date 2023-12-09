package bg.softuni.servicesvratsa.repository;

import bg.softuni.servicesvratsa.model.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {


    Optional<ServiceEntity> findByServiceId(String serviceId);

    Optional<ServiceEntity> findByName(String name);
}
