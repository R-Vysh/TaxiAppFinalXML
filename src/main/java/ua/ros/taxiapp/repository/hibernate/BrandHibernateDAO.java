package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.ros.taxiapp.domain.Brand;
import ua.ros.taxiapp.repository.BrandDAO;

public class BrandHibernateDAO extends GenericDAOHibernate<Brand, Integer> implements BrandDAO {

    @Override
    @Transactional
    public Brand findByName(String name) {
        Brand brand = null;
        Query query = this.getSession().getNamedQuery("brand.with.name");
        query.setParameter("brandName", name);
        brand = findOne(query);
        return brand;
    }
}
