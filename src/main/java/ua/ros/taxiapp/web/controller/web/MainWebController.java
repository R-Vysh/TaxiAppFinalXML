package ua.ros.taxiapp.web.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(MainWebController.class);

    @Autowired
    UserService userService;

    @Autowired
    CustomerService customerService;

    @Autowired
    TaxistService taxistService;

    @Autowired
    SessionUserChecker sessionUserChecker;

    @RequestMapping("/main")
    public String mainMenu(Principal principal, HttpSession session, Model model) {
        sessionUserChecker.checkUser(principal, session);
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
