package bg.softuni.mobilelele.repository;

import bg.softuni.mobilelele.models.entity.UserRoleEntity;
import bg.softuni.mobilelele.models.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByRole(UserRoleEnum roleEnum);
}
