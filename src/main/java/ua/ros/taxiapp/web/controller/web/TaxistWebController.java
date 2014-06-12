package ua.ros.taxiapp.web.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.services.TaxistService;

import javax.validation.ConstraintViolationException;

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
