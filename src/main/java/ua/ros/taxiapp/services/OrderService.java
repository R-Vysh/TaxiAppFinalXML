package ua.ros.taxiapp.services;

import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;

import java.io.Serializable;
import java.util.List;

public interface OrderService {

    public List<Order> getAllOrders();

    public boolean createOrder(Order order, Customer customer);

    public Order findById(Integer id);

    public boolean deleteOrder(Order order);

    public boolean updateOrder(Order order);

    public boolean takeOrder(Order order);

    public boolean cancelOrder(Order order);

    List<Order> findByCustomer(Customer customer);

    boolean createDetailedOrder(Order order, Customer customer, String modelName, String brandName, Double pricePerKmHigh, Double pricePerKmLow);
}
