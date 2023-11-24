package bg.softuni.servicesvratsa.repository;

import bg.softuni.servicesvratsa.model.entity.CartEntity;
import bg.softuni.servicesvratsa.model.view.CartViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    @Override
    List<CartEntity> findAll();

    List<CartEntity> findAllByUsername(String username);

    Optional<CartEntity> findByProductId(String productId);

    void deleteByProductId(String productId);
}
