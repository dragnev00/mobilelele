package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.models.service.BrandServiceModel;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface BrandService {

    void initializeBrand();

    Set<BrandServiceModel> getAllBrands();
}
