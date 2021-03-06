package bg.softuni.mobilelele.repository;

import bg.softuni.mobilelele.models.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

    void deleteById(Long id);
}
