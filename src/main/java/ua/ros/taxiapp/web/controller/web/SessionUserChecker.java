package ua.ros.taxiapp.web.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.services.CustomerService;
import ua.ros.taxiapp.services.TaxistService;
import ua.ros.taxiapp.services.UserService;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Component
public class SessionUserChecker {
    @Autowired
    UserService userService;

    @Autowired
    CustomerService customerService;

    @Autowired
    TaxistService taxistService;

    public void checkUser(Principal principal, HttpSession session) {
        if ((session.getAttribute("taxist") == null) && (session.getAttribute("customer") == null)) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            if (!user.getTaxist()) {
                Customer customer = customerService.findByUser(user);
                session.setAttribute("customer", customer);
            } else {
                session.setAttribute("taxist", taxistService.findByUser(user));
            }
        }
    }
}