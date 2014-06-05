package ua.ros.taxiapp.web.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.services.CustomerService;
import ua.ros.taxiapp.services.TaxistService;
import ua.ros.taxiapp.services.UserService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/web")
public class MainWebController {
    @Autowired
    UserService userService;

    @Autowired
    CustomerService customerService;

    @Autowired
    TaxistService taxistService;

    @RequestMapping("/main")
    public String mainMenu(Principal principal, HttpSession session, Model model) {
        if ((session.getAttribute("taxist") == null) && (session.getAttribute("customer") == null)) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            if (!user.getTaxist()) {
                Customer customer = customerService.findByUser(user);
                session.setAttribute("customer", customer);
            } else {
                session.setAttribute("taxist", taxistService.findByUser(user));
                return "mainTaxist";
            }
        }
        if (session.getAttribute("taxist") != null) {
            return "mainTaxist";
        } else {
            List<Taxist> taxists = taxistService.getAllFreeTaxists();
            model.addAttribute("freeTaxis", taxists);
            model.addAttribute("order", new Order());
            return "mainCustomer";
        }
    }
}
