package ua.ros.taxiapp.web.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.ros.taxiapp.domain.Car;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.services.CarService;
import ua.ros.taxiapp.services.TaxistService;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.security.Principal;

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
            Car curCar = ((Taxist)session.getAttribute("taxist")).getCar();
            curCar.updateFromCar(car);
            if (carService.updateCar(curCar)) {
                    redirectAttributes.addAttribute("updatedCar", true);
                    return "redirect:/web/main";
            }
        } catch (ConstraintViolationException ex) {
            model.addAttribute("wrongData", true);
        }
        model.addAttribute("updateUnsuccessful", true);
        return "changeCarInfo";
    }
}
