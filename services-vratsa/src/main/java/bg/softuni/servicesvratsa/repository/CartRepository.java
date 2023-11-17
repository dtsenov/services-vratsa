package bg.softuni.servicesvratsa.repository;

import bg.softuni.servicesvratsa.model.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    @Override
    List<CartEntity> findAll();
}
