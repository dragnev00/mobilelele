package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.models.view.OfferDetailsView;
import bg.softuni.mobilelele.models.view.OfferSummaryView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OfferService {

    void initializeOffer();

    List<OfferSummaryView> getAllOffers();

    OfferDetailsView findById(Long id);

    void deleteOffer(Long id);
}
