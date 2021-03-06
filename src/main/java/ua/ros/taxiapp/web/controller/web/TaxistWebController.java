package ua.ros.taxiapp.web.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.ros.taxiapp.domain.Authority;
import ua.ros.taxiapp.domain.Coordinates;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.services.CustomerService;
import ua.ros.taxiapp.services.ModelService;
import ua.ros.taxiapp.services.TaxistService;

import javax.validation.ConstraintViolationException;
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

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
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
                                 @RequestParam(value = "confirmPassword") String confirmPassword,
                                 Model model, RedirectAttributes redirectAttributes) {
        if (!confirmPassword.equals(taxist.getUser().getPassword())) {
            model.addAttribute("registrationUnsuccessful", true);
            return "registerCustomer";
        }
        try {

            if (taxistService.createTaxist(taxist)) {
                redirectAttributes.addAttribute("registrationSuccessful", true);
                return "redirect:/web/login";
            }
        } catch (ConstraintViolationException ex) {
            model.addAttribute("wrongData", true);
        }
        model.addAttribute("registrationUnsuccessful", true);
        return "registerTaxist";
    }
}
