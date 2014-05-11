package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.repository.UserDAO;

public class UserHibernateDAO extends GenericDAOHibernate<User, Integer> implements UserDAO {
    
    @Override
    public User findByMobile(String mobile) {
        User user = null;
        String hql = "FROM users WHERE MOBILE = :mobile";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("mobile", mobile);
        user = findOne(query);
        return user;
    }

}
