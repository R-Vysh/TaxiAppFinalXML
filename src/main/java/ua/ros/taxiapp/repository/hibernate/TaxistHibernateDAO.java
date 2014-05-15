package ua.ros.taxiapp.repository.hibernate;

import org.springframework.stereotype.Repository;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.repository.TaxistDAO;

import java.util.List;

@Repository
public class TaxistHibernateDAO extends GenericDAOHibernate<Taxist, Integer> implements TaxistDAO {

    @Override
    public Taxist findByMobile(String mobile) {
        return null;
    }

    @Override
    public List<Taxist> findAllFree() {
        return null;
    }
}
