package ua.ros.taxiapp.web.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.services.CarService;
import ua.ros.taxiapp.services.CustomerService;
import ua.ros.taxiapp.services.OrderService;
import ua.ros.taxiapp.services.TaxistService;

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
    TaxistService taxistService;

    @Autowired
    CarService carService;

    @Autowired
    SessionUserChecker sessionUserChecker;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setTaxistService(TaxistService taxistService) {
        this.taxistService = taxistService;
    }

    public void setSessionUserChecker(SessionUserChecker sessionUserChecker) {
        this.sessionUserChecker = sessionUserChecker;
    }

    @RequestMapping(value = "/make-order", method = RequestMethod.POST)
    public String makeOrder(@ModelAttribute(value = "order") Order order,
                            Model model, HttpSession session, RedirectAttributes redirectAttrs) {
        Customer customer = (Customer) session.getAttribute("customer");
        if(order.getToPlace().equals("") || order.getFromPlace().equals("")) {
            redirectAttrs.addAttribute("orderSuccessful", false);
            return "redirect:/web/main-customer";
        }
        if (orderService.createOrder(order, customer)) {
            redirectAttrs.addAttribute("orderSuccessful", true);
            return "redirect:/web/main-customer";
        }
        redirectAttrs.addAttribute("orderSuccessful", false);
        return "redirect:/web/main-customer";
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String showHistory(@RequestParam(value = "page") Integer page,
                              Principal principal, Model model, HttpSession session) {
        sessionUserChecker.checkUser(principal, session);
        Customer customer = (Customer) session.getAttribute("customer");
        List<Order> orders = orderService.findByCustomer(customer, page);
        Double outcome = orderService.countOutcome(customer);
        model.addAttribute("outcome", outcome);
        model.addAttribute("orders", orders);
        return "customerHistory";
    }

    @RequestMapping(value = "/history-taxist", method = RequestMethod.GET)
    public String showHistoryTaxist(Principal principal, Model model, HttpSession session) {
        sessionUserChecker.checkUser(principal, session);
        Taxist taxist = (Taxist) session.getAttribute("taxist");
        List<Order> orders = orderService.findByTaxist(taxist);
        Double income = orderService.countIncome(taxist);
        model.addAttribute("orders", orders);
        model.addAttribute("income", income);
        return "taxistHistory";
    }

    @RequestMapping(value = "/detailed-order", method = RequestMethod.GET)
    public String detailedOrder(Principal principal, Model model, HttpSession session) {
        sessionUserChecker.checkUser(principal, session);
        model.addAttribute("order", new Order());
        List<Taxist> taxis = taxistService.getAllFreeTaxists();
        List<String> models = carService.allModels();
        List<String> brands = carService.allBrands();
        models.add(0,null);
        brands.add(0,null);
        model.addAttribute("freeTaxis", taxis);
        model.addAttribute("allModels", models);
        model.addAttribute("allBrands", brands);
        return "detailedOrder";
    }

    @RequestMapping(value = "/detailed-order", method = RequestMethod.POST)
    public String makeDetailedOrder(@ModelAttribute(value = "order") Order order,
                                    @RequestParam(value = "model", required = false) String modelName,
                                    @RequestParam(value = "brand", required = false) String brandName,
                                    @RequestParam(value = "pricePerKmHigh", required = false) String pricePerKmHigh,
                                    @RequestParam(value = "pricePerKmLow", required = false) String pricePerKmLow,
                                    Model model, HttpSession session, RedirectAttributes redirectAttrs) {
        Customer customer = (Customer) session.getAttribute("customer");
        if(order.getToPlace().equals("") || order.getFromPlace().equals("")) {
            redirectAttrs.addAttribute("orderSuccessful", false);
            return "redirect:/web/main-customer";
        }
        Double priceHigh=null;
        Double priceLow=null;
        if(pricePerKmHigh!=null && !pricePerKmHigh.isEmpty()) {
            priceHigh = Double.parseDouble(pricePerKmHigh);
        }
        if(pricePerKmLow!=null && !pricePerKmLow.isEmpty()) {
            priceLow = Double.parseDouble(pricePerKmLow);
        }
        if (orderService.createDetailedOrder(order, customer, modelName, brandName, priceHigh, priceLow)) {
            redirectAttrs.addAttribute("orderSuccessful", true);
            return "redirect:/web/main-customer";
        }
        redirectAttrs.addAttribute("orderSuccessful", false);
        return "redirect:/web/main-customer";
    }

    @RequestMapping(value = "/cancel-order/{id}", method = RequestMethod.GET)
    public String cancelOrder(@PathVariable(value = "id") Integer id, Principal principal,
                              RedirectAttributes redirectAttributes, HttpSession session) {
        sessionUserChecker.checkUser(principal, session);
        System.out.println("ID = " + id);
        Customer customer = (Customer) session.getAttribute("customer");
        Order order = orderService.findById(id);
        if (order != null) {
            if (order.getCustomer().equals(customer)) {
                if(orderService.cancelOrder(order)) {
                    redirectAttributes.addAttribute("orderCancelled", true);
                    return "redirect:/web/main-customer";
                }
            }
        }
        redirectAttributes.addAttribute("orderCancelled", false);
        return "redirect:/web/main-customer";
    }

    @RequestMapping(value = "/on-place", method = RequestMethod.GET)
    public String onPlaceOrder(Principal principal, HttpSession session) {
        sessionUserChecker.checkUser(principal, session);
        Taxist taxist = (Taxist) session.getAttribute("taxist");
        Order order = taxist.getCurrentOrder();
        if (order != null) {
            orderService.onPlaceOrder(order);
        }
        return "redirect:/web/main";
    }

    @RequestMapping(value = "/finish", method = RequestMethod.GET)
    public String finishOrder(Principal principal, HttpSession session) {
        sessionUserChecker.checkUser(principal, session);
        Taxist taxist = (Taxist) session.getAttribute("taxist");
        Order order = taxist.getCurrentOrder();
        if (order != null) {
            orderService.finishOrder(order);
        }
        return "redirect:/web/main";
    }

    @RequestMapping(value = "/take-order/{orderId}", method = RequestMethod.GET)
    public String takeOrder(@PathVariable(value = "orderId") Integer orderId,
                            @RequestParam(value = "price") Double price,
                            Principal principal, HttpSession session) {
        sessionUserChecker.checkUser(principal, session);
        Taxist taxist = (Taxist) session.getAttribute("taxist");
        Order order = orderService.findById(orderId);
        if (order != null && order.getStatus().equals(Order.OrderStatus.NOTTAKEN)) {
            order.setPrice(price);
            orderService.takeOrder(order, taxist);
        }
        return "redirect:/web/main";
    }

    @RequestMapping(value = "/set-blamed/{orderId}", method = RequestMethod.GET)
    public String takeOrder(@PathVariable(value = "orderId") Integer orderId,
                            Principal principal, HttpSession session) {
        Order order = orderService.findById(orderId);
        if (order != null) {
            order.setBlamed(true);
            orderService.updateOrder(order);
        }
        return "redirect:/web/order/history-taxist";
    }
}
