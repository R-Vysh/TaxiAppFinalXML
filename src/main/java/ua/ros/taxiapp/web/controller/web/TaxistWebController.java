package ua.ros.taxiapp.web.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.ros.taxiapp.domain.Authority;
import ua.ros.taxiapp.domain.Coordinates;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.services.CustomerService;
import ua.ros.taxiapp.services.ModelService;
import ua.ros.taxiapp.services.TaxistService;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/web/taxist")
public class TaxistWebController {
    // private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    TaxistService taxistService;

    @Autowired
    ModelService modelService;

    public void setTaxistService(TaxistService taxistService) {
        this.taxistService = taxistService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage(Model model) {
        Taxist taxist = new Taxist();
        List<ua.ros.taxiapp.domain.Model> models = modelService.getAllModels();
        model.addAttribute("taxist", taxist);
        model.addAttribute("allModels", models);
        return "registerTaxist";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerTaxist(@ModelAttribute(value = "taxist") Taxist taxist,
                                 Model model) {
        if (taxistService.createTaxist(taxist)) {
            model.addAttribute("registrationSuccessful", true);
            return "login";
        }
        model.addAttribute("registrationUnsuccessful", true);
        return "registerTaxist";
    }
}
