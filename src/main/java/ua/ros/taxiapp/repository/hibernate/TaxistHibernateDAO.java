package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.repository.TaxistDAO;

import java.util.List;

@Repository
public class TaxistHibernateDAO extends GenericDAOHibernate<Taxist, Integer> implements TaxistDAO {

    @Override
    public Taxist findByMobile(String mobile) {
        Query query = this.getSession().getNamedQuery("taxist.with.mobile");
        query.setParameter("mobile", mobile);
        Taxist taxist = findOne(query);
        return taxist;
    }

    @Override
    public List<Taxist> findAllFree() {
        Query query = this.getSession().getNamedQuery("taxist.free");
        List<Taxist> taxists = query.list();
        return taxists;
    }
}
