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
import ua.ros.taxiapp.services.TaxistService;

import java.util.HashSet;

@Controller
@RequestMapping("/web/taxist")
public class TaxistWebController {
    // private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    TaxistService taxistService;

    public void setTaxistService(TaxistService taxistService) {
        this.taxistService = taxistService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage(Model model) {
        Taxist taxist = new Taxist();
        model.addAttribute("taxist", taxist);
        return "registerTaxist";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerTaxist(@ModelAttribute(value = "taxist") Taxist taxist,
                                    Model model) {
        taxist.getUser().setTaxist(true);
        taxist.setCoordinates(new Coordinates());
        taxist.setCurrentOrder(null);
        taxistService.createNewTaxist(taxist);
        Authority authority = new Authority();
        authority.setRolename(Authority.Rolename.ROLE_TAXIST);
        authority.setUser(taxist.getUser());
        HashSet<Authority> auth = new HashSet<>();
        auth.add(authority);
        taxist.getUser().setAuthorities(auth);
        if (taxistService.updateTaxist(taxist)) {
            model.addAttribute("registrationSuccessful", true);
            return "login";
        }
        model.addAttribute("registrationUnsuccessful", true);
        return "registerTaxist";
    }
}
