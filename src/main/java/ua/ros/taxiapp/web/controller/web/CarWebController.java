package ua.ros.taxiapp.web.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.ros.taxiapp.domain.*;
import ua.ros.taxiapp.services.CarService;
import ua.ros.taxiapp.services.CustomerService;
import ua.ros.taxiapp.services.TaxistService;
import ua.ros.taxiapp.web.controller.mobile.StatusMessage;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

@Controller
@RequestMapping("/web/car")
public class CarWebController {
    // private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    SessionUserChecker sessionUserChecker;

    @Autowired
    CarService carService;

    @Autowired
    TaxistService taxistService;

    @RequestMapping(value = "/change-info", method = RequestMethod.GET)
    public String getCarChangePage(Principal principal, HttpSession session, Model model) {
        sessionUserChecker.checkUser(principal, session);
        Taxist taxist = (Taxist) session.getAttribute("taxist");
        Car car = taxist.getCar();
        model.addAttribute("car", car);
        return "changeCarInfo";
    }

    @RequestMapping(value = "/change-info", method = RequestMethod.POST)
    public String registerCustomer(@ModelAttribute(value = "car") @Valid Car car,
                                   RedirectAttributes redirectAttributes,
                                   Model model, HttpSession session) {
        try {
            if (carService.updateCar(car)) {
                Taxist taxist = (Taxist) session.getAttribute("taxist");
                taxist.setCar(car);
                if (taxistService.updateTaxist(taxist)) {
                    redirectAttributes.addAttribute("updatedCar", true);
                    return "redirect:/web/main";
                }
            }
        } catch (ConstraintViolationException ex) {
            model.addAttribute("wrongData", true);
        }
        model.addAttribute("updateUnsuccessful", true);
        return "changeCarInfo";
    }
}
