package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.repository.UserDAO;

@Repository
public class UserHibernateDAO extends GenericDAOHibernate<User, Integer> implements UserDAO {
    
    @Override
    public User findByMobile(String mobile) {
        Query query = this.getSession().getNamedQuery("user.with.mobile");
        query.setParameter("mobile", mobile);
        User user = findOne(query);
        return user;
    }

}
