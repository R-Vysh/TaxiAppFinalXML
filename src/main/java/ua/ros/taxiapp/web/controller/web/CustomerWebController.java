package ua.ros.taxiapp.web.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.ros.taxiapp.domain.Authority;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.services.CustomerService;
import ua.ros.taxiapp.web.controller.mobile.StatusMessage;

import java.util.List;

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

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerCustomer(@ModelAttribute(value = "customer") Customer customer, Model model) {
        customer.getUser().setTaxist(false);
        Authority authority = new Authority();
        authority.setRolename(Authority.Rolename.ROLE_CUST);
        authority.setUser(customer.getUser());
        customer.getUser().setAuthority(authority);
        if (customerService.createCustomer(customer)) {
            model.addAttribute("registrationSuccessful", true);
            return "login";
        }
        model.addAttribute("registrationUnsuccessful", true);
        return "registerCustomer";
    }

//    @RequestMapping(value = "/registration-successful")
//    public String registrationSuccessful(Model model) {
//        model.addAttribute("registrationSuccessful", true);
//        return "login";
//    }
}
