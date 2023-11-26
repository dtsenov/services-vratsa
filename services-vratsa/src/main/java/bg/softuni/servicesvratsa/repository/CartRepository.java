package bg.softuni.servicesvratsa.repository;

import bg.softuni.servicesvratsa.model.entity.CartEntity;
import bg.softuni.servicesvratsa.model.view.CartViewModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Modifying
    @Transactional
    @Query("DELETE FROM CartEntity c WHERE c.username = :username")
    void deleteByUsername(@Param("username") String username);


}
