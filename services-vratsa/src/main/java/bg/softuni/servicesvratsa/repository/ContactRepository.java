package bg.softuni.servicesvratsa.repository;

import bg.softuni.servicesvratsa.model.entity.ContactEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

    @Query("SELECT c FROM ContactEntity c " +
            "WHERE c.isAnswered = false")
    List<ContactEntity> getAllByAnsweredIsFalse();

    @Query("SELECT c FROM ContactEntity c " +
            "WHERE c.isAnswered = true")
    List<ContactEntity> getAllByAnsweredIsTrue();
}
