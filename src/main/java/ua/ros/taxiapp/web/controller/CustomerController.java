package ua.ros.taxiapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.message.ShowStatusMessage;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.services.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/rest/customers")
public class CustomerController {
     // private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
        
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public StatusMessage saveCustomer(@RequestParam("mobile") String mobile, @RequestParam("password") String password,
                                @RequestParam("username") String username) {
        User user = new User();
        user.setMobile(mobile);
        user.setPassword(password);
        user.setUsername(username);
        user.setTaxist(false);
        Customer customer = new Customer();
        customer.setUser(user);
        if(customerService.createCustomer(customer)) {
        return new StatusMessage(StatusMessage.OK);
        } else {
            return new StatusMessage(StatusMessage.FAIL);
        }
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Customer findCustomer(@PathVariable("id") Integer id) {
        return customerService.findById(id);
    }

    @RequestMapping(value = "/withmobile", method = RequestMethod.GET)
    @ResponseBody
    public Customer findCustomerWithMobile(@RequestParam("mobile") String mobile) {
        return customerService.findByMobile(mobile);
    }
}
