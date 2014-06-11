package ua.ros.taxiapp.services;

import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.domain.Taxist;

import java.io.Serializable;
import java.util.List;

public interface OrderService {

    public List<Order> getAllOrders();

    public boolean createOrder(Order order, Customer customer);

    public boolean saveOrder(Order order);

    public Order findById(Integer id);

    public boolean deleteOrder(Order order);

    public boolean updateOrder(Order order);

    public boolean takeOrder(Order order, Taxist taxist);

    public boolean cancelOrder(Order order);

    public List<Order> findByCustomer(Customer customer);

    public boolean createDetailedOrder(Order order, Customer customer, String modelName, String brandName, Double pricePerKmHigh, Double pricePerKmLow);

    public boolean onPlaceOrder(Order order);

    public boolean finishOrder(Order order);

    public List<Order> findOrdersForTaxist(Taxist taxist);

    public List<Order> findByTaxist(Taxist taxist);

    public Double countIncome(Taxist taxist);

    public Double countOutcome(Customer customer);
}
