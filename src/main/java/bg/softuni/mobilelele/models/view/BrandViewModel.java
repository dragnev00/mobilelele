package bg.softuni.mobilelele.models.view;

import bg.softuni.mobilelele.models.service.ModelServiceModel;

import java.util.Set;

public class BrandViewModel {

    private String name;
    private Set<ModelServiceModel> models;

    public String getName() {
        return name;
    }

    public BrandViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public Set<ModelServiceModel> getModels() {
        return models;
    }

    public BrandViewModel setModels(Set<ModelServiceModel> models) {
        this.models = models;
        return this;
    }
}
