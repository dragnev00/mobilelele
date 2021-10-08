package bg.softuni.mobilelele.models.entity;

import bg.softuni.mobilelele.models.entity.enums.UserRoleEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;
    @ManyToMany(mappedBy = "userRoles")
    private Set<UserEntity> users;


    public Long getId() {
        return id;
    }

    public UserRoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public UserRoleEntity setUsers(Set<UserEntity> users) {
        this.users = users;
        return this;
    }
}
