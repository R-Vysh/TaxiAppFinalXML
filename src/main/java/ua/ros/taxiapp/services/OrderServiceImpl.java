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

import java.util.List;

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
        if (saveOrder(order) == false) {
            return false;
        }
        if (customerService.updateCustomer(customer)) {
            logger.info("User " + customer.getUser().getUsername() + " made an order. Id: " + order.getOrderId());
            return true;
        }
        return false;
    }

    @Override
    public boolean saveOrder(Order order) {
        try {
            orderDAO.save(order);
        } catch (DataAccessException ex) {
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
    public boolean takeOrder(Order order, Taxist taxist) {
        if (taxist.getCurrentOrder() != null) {
            return false;
        }
        order.setTaxist(taxist);
        order.setStatus(Order.OrderStatus.TAKEN);
        taxist.setCurrentOrder(order);
        taxist.setFree(false);
        return updateOrder(order) && taxistService.updateTaxist(taxist);
    }

    @Override
    public boolean cancelOrder(Order order) {
        order.setStatus(Order.OrderStatus.CANCELED);
        Taxist taxist = order.getTaxist();
        Customer customer = order.getCustomer();
        if (taxist != null) {
            taxist.setCurrentOrder(null);
            taxist.setFree(true);
            taxistService.updateTaxist(taxist);
        }
        if (customer.getCurrentOrder() != null && customer.getCurrentOrder().equals(order)) {
            customer.setCurrentOrder(null);
        }
        return updateOrder(order) && customerService.updateCustomer(customer);
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
    public boolean onPlaceOrder(Order order) {
        order.setStatus(Order.OrderStatus.ONPLACE);
        return updateOrder(order);
    }

    @Override
    public boolean finishOrder(Order order) {
        order.setStatus(Order.OrderStatus.DONE);
        Taxist taxist = order.getTaxist();
        Customer customer = order.getCustomer();
        taxist.setCurrentOrder(null);
        taxist.setFree(true);
        customer.setCurrentOrder(null);
        return taxistService.updateTaxist(taxist) && updateOrder(order) && customerService.updateCustomer(customer);
    }

    @Override
    public List<Order> findOrdersForTaxist(Taxist taxist) {
        return orderDAO.findForTaxist(taxist);
    }

    @Override
    public List<Order> findByTaxist(Taxist taxist) {
        return orderDAO.findByTaxist(taxist);
    }

    @Override
    public Double countIncome(Taxist taxist) {
        return orderDAO.countIncome(taxist);
    }

    @Override
    public Double countOutcome(Customer customer) {
        return orderDAO.countOutcome(customer);
    }

    @Override
    public List<Order> findByCustomer(Customer customer, Integer page) {
        return orderDAO.findByCustomer(customer, page);
    }
}
