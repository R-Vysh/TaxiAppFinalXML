package ua.ros.taxiapp.web.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.services.CustomerService;
import ua.ros.taxiapp.services.OrderService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

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
        if (orderService.createOrder(order, customer)) {
            model.addAttribute("orderSuccessful", true);
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

    @RequestMapping(value = "/detailed-order", method = RequestMethod.GET)
    public String detailedOrder(Principal principal, Model model, HttpSession session) {
        sessionUserChecker.checkUser(principal, session);
        model.addAttribute("order", new Order());
        return "detailedOrder";
    }

    @RequestMapping(value = "/detailed-order", method = RequestMethod.POST)
    public String makeDetailedOrder(@ModelAttribute(value = "order") Order order,
                                    @RequestParam(value = "model", required = false) String modelName,
                                    @RequestParam(value = "brand", required = false) String brandName,
                                    @RequestParam(required = false, value = "pricePerKmHigh") String pricePerKmHigh,
                                    @RequestParam(required = false, value = "pricePerKmLow") String pricePerKmLow,
                                    Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        Double priceHigh=null;
        Double priceLow=null;
        if(pricePerKmHigh!=null && !pricePerKmHigh.isEmpty()) {
            priceHigh = Double.parseDouble(pricePerKmHigh);
        }
        if(pricePerKmLow!=null && !pricePerKmLow.isEmpty()) {
            priceLow = Double.parseDouble(pricePerKmLow);
        }
        if (orderService.createDetailedOrder(order, customer, modelName, brandName, priceHigh, priceLow)) {
            model.addAttribute("orderSuccessful", true);
            return "mainCustomer";
        }
        model.addAttribute("orderUnsuccessful", true);
        return "mainCustomer";
    }

    @RequestMapping(value = "/cancel-order", method = RequestMethod.GET)
    public String cancelOrder(Principal principal, HttpSession session) {
        sessionUserChecker.checkUser(principal, session);
        Customer customer = (Customer) session.getAttribute("customer");
        Order order = customer.getCurrentOrder();
        if (order != null) {
            orderService.cancelOrder(order);
            customerService.cancelOrder(customer);
        }
        return "redirect:/web/main";
    }
}
