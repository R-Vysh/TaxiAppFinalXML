package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import ua.ros.taxiapp.domain.Brand;
import ua.ros.taxiapp.repository.BrandDAO;

public class BrandHibernateDAO extends GenericDAOHibernate<Brand, Integer> implements BrandDAO {

    @Override
    public Brand findByName(String name) {
        Brand brand = null;
        String hql = "FROM ua.ros.taxiapp.domain.Brand b WHERE brandsName = :name";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("name", name);
        brand = findOne(query);
        return brand;
    }
}
