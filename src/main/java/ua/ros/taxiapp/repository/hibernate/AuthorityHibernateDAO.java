package ua.ros.taxiapp.repository.hibernate;

import org.springframework.stereotype.Repository;
import ua.ros.taxiapp.domain.Authority;
import ua.ros.taxiapp.repository.AuthorityDAO;

@Repository
public class AuthorityHibernateDAO extends GenericDAOHibernate<Authority, Integer> implements AuthorityDAO {

}
