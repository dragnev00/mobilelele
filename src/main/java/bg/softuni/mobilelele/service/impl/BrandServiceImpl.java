package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.models.entity.BrandEntity;
import bg.softuni.mobilelele.models.service.BrandServiceModel;
import bg.softuni.mobilelele.models.service.ModelServiceModel;
import bg.softuni.mobilelele.repository.BrandRepository;
import bg.softuni.mobilelele.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initializeBrand() {
        if (brandRepository.count() == 0) {
            BrandEntity ford = new BrandEntity();
            ford.setName("Ford");

            brandRepository.save(ford);
        }
    }

    @Override
    public Set<BrandServiceModel> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(brandEntity -> {
                    BrandServiceModel brandServiceModel = modelMapper.map(brandEntity, BrandServiceModel.class);
                    brandServiceModel.getModels()
                            .stream()
                            .map(modelEntity -> modelMapper.map(modelEntity, ModelServiceModel.class))
                            .collect(Collectors.toSet());
                    return brandServiceModel;
                })
                .collect(Collectors.toSet());
    }
}
