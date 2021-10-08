package bg.softuni.mobilelele.models.entity;

import bg.softuni.mobilelele.models.entity.enums.CategoryEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;
    @Column(name = "image_url",
            nullable = false)
    private String imageUrl;
    @Column(name = "start_year",
            nullable = false)
    private Integer startYear;
    @Column(name = "end_year")
    private Integer endYear;
    @ManyToOne
    private BrandEntity brand;
    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    private Set<OfferEntity> offers;

    public String getName() {
        return name;
    }

    public ModelEntity setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public ModelEntity setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ModelEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public ModelEntity setStartYear(Integer startYear) {
        this.startYear = startYear;
        return this;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public ModelEntity setEndYear(Integer endYear) {
        this.endYear = endYear;
        return this;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public ModelEntity setBrand(BrandEntity brand) {
        this.brand = brand;
        return this;
    }

    public Set<OfferEntity> getOffers() {
        return offers;
    }

    public ModelEntity setOffers(Set<OfferEntity> offers) {
        this.offers = offers;
        return this;
    }
}
