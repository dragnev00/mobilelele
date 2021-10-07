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

    public Set<OfferEntity> getOffers() {
        return offers;
    }

    public void setOffers(Set<OfferEntity> offers) {
        this.offers = offers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }
}
