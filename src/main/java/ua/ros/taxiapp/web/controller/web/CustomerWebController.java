package ua.ros.taxiapp.web.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.ros.taxiapp.domain.Authority;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.services.CustomerService;
import ua.ros.taxiapp.web.controller.mobile.StatusMessage;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

@Controller
@RequestMapping("/web/customer")
public class CustomerWebController {
    // private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "registerCustomer";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerCustomer(@ModelAttribute(value = "customer") @Valid Customer customer,
                                   @RequestParam(value = "confirmPassword") String confirmPassword,
                                   RedirectAttributes redirectAttributes,
                                   Model model) {
        if(!confirmPassword.equals(customer.getUser().getPassword())) {
            model.addAttribute("registrationUnsuccessful", true);
            return "registerCustomer";
        }
        try {
        if (customerService.createCustomer(customer)) {
            redirectAttributes.addAttribute("registrationSuccessful", true);
            return "redirect:/web/login";
        }
        } catch (ConstraintViolationException ex) {
            model.addAttribute("wrongData", true);
        }
        model.addAttribute("registrationUnsuccessful", true);
        return "registerCustomer";
    }
}
