package bg.softuni.mobilelele.models.service;

import java.util.Set;

public class BrandServiceModel {

    private String name;
    private Set<ModelServiceModel> models;

    public String getName() {
        return name;
    }

    public BrandServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public Set<ModelServiceModel> getModels() {
        return models;
    }

    public BrandServiceModel setModels(Set<ModelServiceModel> models) {
        this.models = models;
        return this;
    }
}
