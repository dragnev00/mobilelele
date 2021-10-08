package bg.softuni.mobilelele.models.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private Set<ModelEntity> models;

    public String getName() {
        return name;
    }

    public BrandEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Set<ModelEntity> getModels() {
        return models;
    }

    public BrandEntity setModels(Set<ModelEntity> models) {
        this.models = models;
        return this;
    }
}
