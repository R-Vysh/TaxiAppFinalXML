package ua.ros.taxiapp.web.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.services.CustomerService;
import ua.ros.taxiapp.services.OrderService;
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
    OrderService orderService;

    @Autowired
    SessionUserChecker sessionUserChecker;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setTaxistService(TaxistService taxistService) {
        this.taxistService = taxistService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setSessionUserChecker(SessionUserChecker sessionUserChecker) {
        this.sessionUserChecker = sessionUserChecker;
    }

    @RequestMapping("/main")
    public String mainMenu(Principal principal, HttpSession session,
                           @RequestParam(value = "orderSuccessful", required = false) final Boolean orderSuccessful,
                           @RequestParam(value = "updatedCar", required = false) final Boolean carUpdated,
                           Model model) {
        sessionUserChecker.checkUser(principal, session);
        if (session.getAttribute("taxist") != null) {
            Taxist taxist = (Taxist) session.getAttribute("taxist");
            List<Order> orders = orderService.findOrdersForTaxist(taxist);
            model.addAttribute("availableOrders", orders);
            if (carUpdated != null) {
                model.addAttribute("carUpdated", true);
            }
            return "mainTaxist";
        } else {
            Customer customer = (Customer) session.getAttribute("customer");
            model.addAttribute("currentOrder", customer.getCurrentOrder());
            model.addAttribute("order", new Order());
            if (orderSuccessful != null) {
                if (orderSuccessful) {
                    model.addAttribute("orderSuccessful", true);
                } else {
                    model.addAttribute("orderUnsuccessful", true);
                }
            }
            return "mainCustomer";
        }
    }
}
