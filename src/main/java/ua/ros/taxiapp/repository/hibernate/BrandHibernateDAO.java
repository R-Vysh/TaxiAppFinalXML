package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.ros.taxiapp.domain.Brand;
import ua.ros.taxiapp.repository.BrandDAO;

@Repository
public class BrandHibernateDAO extends GenericDAOHibernate<Brand, Integer> implements BrandDAO {

    @Override
    @Transactional
    public Brand findByName(String name) {
        Brand brand = null;
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("brand.with.name");
        query.setParameter("brandName", name);
        brand = (Brand) query.uniqueResult();
        return brand;
    }
}
