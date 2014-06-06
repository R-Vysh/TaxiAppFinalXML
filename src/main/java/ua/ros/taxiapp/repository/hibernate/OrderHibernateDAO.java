package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.repository.OrderDAO;

import java.util.List;

@Repository
public class OrderHibernateDAO extends GenericDAOHibernate<Order, Integer> implements OrderDAO {



    @Override
    @Transactional
    public List<Order> findActiveOrders() {
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("order.active");
        List<Order> orders = query.list();
        return orders;
    }

    @Override
    @Transactional
    public List<Order> findByCustomer(Customer customer) {
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("order.with.customer");
        query.setParameter("customer", customer);
        List<Order> orders = query.list();
        return orders;
    }

    @Override
    @Transactional
    public void takeOrder(Order order) {
        order.setStatus(Order.OrderStatus.TAKEN);
        save(order);
    }

    @Override
    @Transactional
    public void cancelOrder(Order order) {
        order.setStatus(Order.OrderStatus.DECLINED);
        save(order);
    }
}
