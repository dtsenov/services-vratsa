package bg.softuni.vikuslugivratsa.repository;

import bg.softuni.vikuslugivratsa.model.entity.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity, Long> {
}
