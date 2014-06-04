package ua.ros.taxiapp.web.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.services.CustomerService;
import ua.ros.taxiapp.services.TaxistService;
import ua.ros.taxiapp.services.UserService;

import javax.servlet.http.HttpSession;
import java.security.Principal;


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
    public String mainMenu(Principal principal, HttpSession session) {
        if ((session.getAttribute("Taxist") == null) && (session.getAttribute("Customer") == null)) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            if (!user.getTaxist()) {
                session.setAttribute("Customer", customerService.findByUser(user));
                return "mainCustomer";
            } else {
                session.setAttribute("Taxist", taxistService.findByUser(user));
                return "mainTaxist";
            }
        }
        if (session.getAttribute("Taxist") != null) {
            return "mainTaxist";
        } else {
            return "mainCustomer";
        }
    }
}
