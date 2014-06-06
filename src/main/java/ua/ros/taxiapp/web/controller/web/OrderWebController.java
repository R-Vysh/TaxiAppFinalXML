package ua.ros.taxiapp.web.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.ros.taxiapp.domain.Authority;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.services.CustomerService;
import ua.ros.taxiapp.services.OrderService;
import ua.ros.taxiapp.web.controller.mobile.StatusMessage;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

@Controller
@RequestMapping("/web/order")
public class OrderWebController {
    private static final Logger logger = LoggerFactory.getLogger(OrderWebController.class);

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;

    @Autowired
    SessionUserChecker sessionUserChecker;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/make-order", method = RequestMethod.POST)
    public String makeOrder(@ModelAttribute(value = "order") Order order,
                            Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        order.setCustomer(customer);
        order.setStatus(Order.OrderStatus.NOTTAKEN);
        if (orderService.createOrder(order)) {
            customer.setCurrentOrder(order);
            if(customerService.updateCustomer(customer)) {
                model.addAttribute("orderSuccessful", true);
                logger.info("User " + customer.getUser().getUsername() + " made an order ");
            }
            System.out.println(order.getFromPlace());
            return "mainCustomer";
        }
        model.addAttribute("orderUnsuccessful", true);
        return "mainCustomer";
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String showHistory(Principal principal, Model model, HttpSession session) {
        sessionUserChecker.checkUser(principal, session);
        Customer customer = (Customer) session.getAttribute("customer");
        List<Order> orders = orderService.findByCustomer(customer);
        model.addAttribute("orders", orders);
        return "customerOrders";
    }
}
