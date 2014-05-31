package ua.ros.taxiapp.web.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.ros.taxiapp.domain.Coordinates;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.services.CustomerService;
import ua.ros.taxiapp.services.OrderService;

import java.util.List;

@Controller
@RequestMapping("/rest/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Order findOrderById(@PathVariable("id") Integer id) {
        return orderService.findById(id);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public StatusMessage createOrder(@RequestParam("fromLat") String fromLat, @RequestParam("fromLng") String fromLng,
                                     @RequestParam("toLat") String toLat, @RequestParam("toLng") String toLng,
                                     @RequestParam("j_username") String username, @RequestParam("j_password") String password,
                                     @RequestParam("fromAddressName") String fromAddressName,
                                     @RequestParam("toAddressName") String toAddressName) {
        Order order = new Order();
        Coordinates fromCoordinates = new Coordinates();
        fromCoordinates.setLatitude(Double.parseDouble(fromLat));
        fromCoordinates.setLongtitude(Double.parseDouble(fromLng));
        Coordinates toCoordinates = new Coordinates();
        toCoordinates.setLatitude(Double.parseDouble(toLat));
        toCoordinates.setLongtitude(Double.parseDouble(toLng));
        Customer customer = customerService.findByUsernameAndPassword(username, password);
        order.setFromCoordinates(fromCoordinates);
        order.setToCoordinates(toCoordinates);
        order.setCustomer(customer);
        order.setStatus(Order.OrderStatus.NOTTAKEN);
        order.setFromPlace(fromAddressName);
        order.setToPlace(toAddressName);
        if(orderService.createOrder(order)) {
            return new StatusMessage(StatusMessage.OK);
        } else {
            return new StatusMessage(StatusMessage.FAIL);
        }
    }
}
