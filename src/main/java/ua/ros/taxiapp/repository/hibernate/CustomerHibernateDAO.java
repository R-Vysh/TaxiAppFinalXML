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
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("customer.with.order");
        query.setParameter("ord", order);
        cust = (Customer) query.uniqueResult();
        return cust;
    }
    
    @Override
    @Transactional
    public Customer findByMobile(String mobile) {
        Customer cust = null;
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("customer.with.mobile");
        query.setParameter("mobile", mobile);
        cust = (Customer) query.uniqueResult();
        return cust;
    }

//    @Override
//    @Transactional
//    public Customer findByUsernameAndPassword(String username, String password) {
//        Customer cust = null;
//        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("customer.with.username.and.password");
//        query.setParameter("username", username);
//        query.setParameter("password", password);
//        cust = (Customer) query.uniqueResult();
//        return cust;
//    }

    @Override
    @Transactional
    public Customer findByUser(User user) {
        Customer cust = null;
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("customer.with.user");
        query.setParameter("user", user);
        cust = (Customer) query.uniqueResult();
        return cust;
    }
}
