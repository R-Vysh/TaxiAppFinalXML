package ua.ros.taxiapp.repository.hibernate;

import org.springframework.stereotype.Repository;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.repository.OrderDAO;

import java.util.List;

@Repository
public class OrderHibernateDAO extends GenericDAOHibernate<Order, Integer> implements OrderDAO {

    @Override
    public List<Order> findActiveOrders() {
        return null;
    }

    @Override
    public List<Order> findByCustomer(Customer customer) {
        return null;
    }

    @Override
    public boolean takeOrder(Order order) {
        return false;
    }

    @Override
    public boolean cancelOrder(Order order) {
        return false;
    }
}
