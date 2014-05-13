package ua.ros.taxiapp.services;

import ua.ros.taxiapp.domain.Order;

import java.io.Serializable;
import java.util.List;

public interface OrderService {

    public List<Order> requestAllOrders();

    public boolean createOrder(Order order);

    public Order requestOrder(Serializable id);

    public boolean deleteOrder(Serializable id);
}
