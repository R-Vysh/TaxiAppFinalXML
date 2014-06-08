package ua.ros.taxiapp.repository;

import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;

import java.util.List;

public interface OrderDAO extends GenericDAO<Order, Integer> {
    public List<Order> findActiveOrders();

    public List<Order> findByCustomer(Customer customer);

}
