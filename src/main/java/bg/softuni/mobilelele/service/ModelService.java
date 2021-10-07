package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.models.entity.ModelEntity;
import org.springframework.stereotype.Service;

@Service
public interface ModelService {

    void initializeModel();

    ModelEntity findById(Long id);
}
