package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.repository.CustomerDAO;

public class CustomerHibernateDAO extends GenericDAOHibernate<Customer, Integer> implements CustomerDAO {
    
    UserHibernateDAO userDAO = new UserHibernateDAO();
    
    @Override
    public Customer findByCurrentOrder(Order order) {
        Customer cust = null;
        String hql = "FROM customers WHERE ID_ORDER = :orId";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("orId", order.getOrderId());
        cust = findOne(query);
        return cust;
    }
    
    @Override
    public Customer findByMobile(String mobile) {
        Customer cust = null;
        User user = null;
        user = userDAO.findByMobile(mobile);
        String hql = "FROM customers WHERE ID_CUSTOMER = :userId";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("userId", user.getUserId());
        cust = findOne(query);
        return cust;
    }
}
