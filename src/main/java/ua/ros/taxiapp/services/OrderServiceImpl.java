package ua.ros.taxiapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ua.ros.taxiapp.domain.Car;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.repository.OrderDAO;
import ua.ros.taxiapp.web.controller.web.MainWebController;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    CustomerService customerService;

    @Autowired
    CarService carService;

    @Autowired
    TaxistService taxistService;

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
    public boolean createOrder(Order order, Customer customer) {
        order.setCustomer(customer);
        order.setStatus(Order.OrderStatus.NOTTAKEN);
        customer.setCurrentOrder(order);
        try {
            orderDAO.save(order);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            return false;
        }
        if (customerService.updateCustomer(customer)) {
            logger.info("User " + customer.getUser().getUsername() + " made an order ");
            return true;
        }
        return false;
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
        order.setStatus(Order.OrderStatus.CANCELED);
        return updateOrder(order);
    }

    @Override
    public List<Order> findByCustomer(Customer customer) {
        return orderDAO.findByCustomer(customer);
    }

    @Override
    public boolean createDetailedOrder(Order order, Customer customer, String modelName, String brandName, Double pricePerKmHigh, Double pricePerKmLow) {
        List<Car> cars = carService.findWithCriteria(modelName, brandName, pricePerKmHigh, pricePerKmLow);
        order.setAppropriateCars(cars);
        return createOrder(order, customer);
    }

    @Override
    public void onPlaceOrder(Order order) {
        order.setStatus(Order.OrderStatus.ONPLACE);
        updateOrder(order);
    }

    @Override
    public void finishOrder(Order order) {
        order.setStatus(Order.OrderStatus.DONE);
        Taxist taxist = order.getTaxist();
        taxist.setCurrentOrder(null);
        taxist.setFree(true);
        taxistService.updateTaxist(taxist);
        updateOrder(order);
    }
}
