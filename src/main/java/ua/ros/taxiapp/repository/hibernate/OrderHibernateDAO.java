package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.domain.Taxist;
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
    public List<Order> findForTaxist(Taxist taxist) {
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("order.for.car");
        query.setParameter("car", taxist.getCar());
        List<Order> orders = query.list();
        return orders;
    }

    @Override
    @Transactional
    public List<Order> findByTaxist(Taxist taxist) {
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("order.with.taxist");
        query.setParameter("taxist", taxist);
        List<Order> orders = query.list();
        return orders;
    }

    @Override
    @Transactional
    public Double countIncome(Taxist taxist) {
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("income.for.taxist");
        query.setParameter("taxist", taxist);
        Double income = (Double) query.uniqueResult();
        return income;
    }

    @Override
    @Transactional
    public Double countOutcome(Customer customer) {
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("outcome.for.customer");
        query.setParameter("customer", customer);
        Double outcome = (Double) query.uniqueResult();
        return outcome;
    }

    @Override
    @Transactional
    public List<Order> findByCustomer(Customer customer, Integer page) {
        int pageSize = 10;
        Query countQuery = this.getSessionFactory().getCurrentSession().getNamedQuery("count.orders.with.customer");
        countQuery.setParameter("customer", customer);
        Long countResults = (Long) countQuery.uniqueResult();
        System.out.println(countResults);
        int lastPageNumber = (int) ((countResults / pageSize) + 1);
        if (page > lastPageNumber) {
            return null;
        }
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("order.with.customer");
        query.setParameter("customer", customer);
        query.setFirstResult((page-1) * pageSize);
        query.setMaxResults(pageSize);
        List<Order> orders = query.list();
        return orders;
    }
}
