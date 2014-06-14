package ua.ros.taxiapp.web.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.services.CustomerService;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Set;

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
    public String getRegisterPage(Model model, final Customer customer) {
        return "registerCustomer";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerCustomer(@Valid final Customer customer,
                                   @RequestParam(value = "confirmPassword") String confirmPassword,
                                   RedirectAttributes redirectAttributes,
                                   final ModelMap model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registerCustomer";
        }
        if (!confirmPassword.equals(customer.getUser().getPassword())) {
            model.addAttribute("registrationUnsuccessful", true);
            return "registerCustomer";
        }
        try {
            if (customerService.createCustomer(customer)) {
                redirectAttributes.addAttribute("registrationSuccessful", true);
                return "redirect:/web/login";
            }
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> errors = ex.getConstraintViolations();
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<?> violation : errors) {
                sb.append(violation.getPropertyPath() + " " + violation.getMessage() + ". ");
            }
            model.addAttribute("wrongDataMessage", sb.toString());
        }
        model.addAttribute("registrationUnsuccessful", true);
        return "registerCustomer";
    }
}
