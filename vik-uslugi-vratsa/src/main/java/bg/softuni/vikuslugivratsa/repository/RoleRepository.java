package bg.softuni.vikuslugivratsa.repository;

import bg.softuni.vikuslugivratsa.model.entity.RoleEntity;
import bg.softuni.vikuslugivratsa.model.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByRole(RoleNameEnum role);

}
