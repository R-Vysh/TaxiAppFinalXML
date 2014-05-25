package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.repository.UserDAO;

@Repository
public class UserHibernateDAO extends GenericDAOHibernate<User, Integer> implements UserDAO {
    
    @Override
    @Transactional
    public User findByMobile(String mobile) {
        Query query = this.getSession().getNamedQuery("user.with.mobile");
        query.setParameter("mobile", mobile);
        User user = findOne(query);
        return user;
    }

    @Override
    @Transactional
    public User findByPasswordAndUsername(String password, String username) {
        Query query = this.getSession().getNamedQuery("user.with.password.and.username");
        query.setParameter("password", password);
        query.setParameter("username", username);
        User user = findOne(query);
        return user;
    }

}
