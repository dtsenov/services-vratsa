package bg.softuni.servicesvratsa.repository;

import bg.softuni.servicesvratsa.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByProductId(String productId);


    Optional<ProductEntity> findByName(String name);
}
