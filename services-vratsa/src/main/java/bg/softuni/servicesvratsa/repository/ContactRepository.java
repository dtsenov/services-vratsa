package bg.softuni.servicesvratsa.repository;

import bg.softuni.servicesvratsa.model.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
