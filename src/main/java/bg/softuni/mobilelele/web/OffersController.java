package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.service.impl.OfferServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OffersController {

    private final OfferServiceImpl offerService;

    public OffersController(OfferServiceImpl offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public String allOffers(Model model) {
        model.addAttribute("offers",
                offerService.getAllOffers());
        return "offers";
    }


    @GetMapping("/{id}/details")
    public String showOffer(@PathVariable Long id, Model model){
        model.addAttribute("offer", this.offerService.findById(id));
        return "details";
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        return "offer-add";
    }

    @DeleteMapping("/{id}")
    public String deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }
}
