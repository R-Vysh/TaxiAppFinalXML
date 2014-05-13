package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import ua.ros.taxiapp.domain.Brand;
import ua.ros.taxiapp.domain.Model;
import ua.ros.taxiapp.repository.ModelDAO;

import java.util.List;

public class ModelHibernateDAO extends GenericDAOHibernate<Model, Integer> implements ModelDAO {

    @Override
    public Model findByName(String name) {
        Model model = null;
        String hql = "FROM models WHERE NAME = :name";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("name", name);
        model = findOne(query);
        return model;
    }

    @Override
    public List<Model> findByBrand(Brand brand) {
        String hql = "FROM models WHERE ID_BRAND = :brId";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("brId", brand.getBrandId());
        List<Model> models = query.list();
        return models;
    }
}
