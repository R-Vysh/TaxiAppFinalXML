package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.repository.CustomerDAO;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Repository
public class CustomerHibernateDAO extends GenericDAOHibernate<Customer, Integer> implements CustomerDAO {
    
    UserHibernateDAO userDAO = new UserHibernateDAO();
    
    @Override
    @Transactional
    public Customer findByCurrentOrder(Order order) {
        Customer cust = null;
        String hql = "FROM customers WHERE ID_ORDER = :orId";
        Query query = this.getSession().getNamedQuery("customer.with.order");
        query.setParameter("ord", order);
        cust = findOne(query);
        return cust;
    }
    
    @Override
    @Transactional
    public Customer findByMobile(String mobile) {
        Customer cust = null;
        Query query = this.getSession().getNamedQuery("customer.with.mobile");
        query.setParameter("mobile", mobile);
        cust = findOne(query);
        return cust;
    }
}
