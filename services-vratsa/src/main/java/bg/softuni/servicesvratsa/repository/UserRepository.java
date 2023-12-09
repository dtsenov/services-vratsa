package bg.softuni.servicesvratsa.repository;

import bg.softuni.servicesvratsa.model.entity.RoleEntity;
import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findAllByRole(RoleEntity role);

    Optional<UserEntity> findByEmail(String email);
}

