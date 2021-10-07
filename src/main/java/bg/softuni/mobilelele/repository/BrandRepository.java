package bg.softuni.mobilelele.repository;

import bg.softuni.mobilelele.models.entity.BrandEntity;
import bg.softuni.mobilelele.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    Optional<BrandEntity> findByName(String name);
}
