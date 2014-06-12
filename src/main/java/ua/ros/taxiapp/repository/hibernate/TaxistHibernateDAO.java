package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.repository.TaxistDAO;

import java.util.List;

@Repository
public class TaxistHibernateDAO extends GenericDAOHibernate<Taxist, Integer> implements TaxistDAO {

    @Override
    @Transactional
    public Taxist findByMobile(String mobile) {
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("taxist.with.mobile");
        query.setParameter("mobile", mobile);
        Taxist taxist = (Taxist) query.uniqueResult();
        return taxist;
    }

    @Override
    @Transactional
    public List<Taxist> findAllFree() {
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("taxist.free");
        List<Taxist> taxists = query.list();
        return taxists;
    }

    @Override
    @Transactional
    public Taxist findByUser(User user) {
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("taxist.with.user");
        query.setParameter("user", user);
        Taxist taxist = (Taxist) query.uniqueResult();
        return taxist;
    }
}
