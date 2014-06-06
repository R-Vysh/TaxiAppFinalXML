package ua.ros.taxiapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.repository.OrderDAO;
import ua.ros.taxiapp.web.controller.web.MainWebController;

import java.io.Serializable;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderDAO orderDAO;

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.findAll(Order.class);
    }

    @Override
    public boolean createOrder(Order order) {
        try {
            orderDAO.save(order);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Order findById(Integer id) {
        return orderDAO.findByID(Order.class, id);
    }

    @Override
    public boolean deleteOrder(Order order) {
        try {
            orderDAO.delete(order);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateOrder(Order order) {
        try {
            orderDAO.save(order);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean takeOrder(Order order) {
        try {
            orderDAO.takeOrder(order);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean cancelOrder(Order order) {
        try {
            orderDAO.cancelOrder(order);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public List<Order> findByCustomer(Customer customer) {
        return orderDAO.findByCustomer(customer);
    }

}
