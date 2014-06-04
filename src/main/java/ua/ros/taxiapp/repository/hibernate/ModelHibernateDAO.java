package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import ua.ros.taxiapp.domain.Brand;
import ua.ros.taxiapp.domain.Model;
import ua.ros.taxiapp.repository.ModelDAO;

import java.util.List;

@Repository
public class ModelHibernateDAO extends GenericDAOHibernate<Model, Integer> implements ModelDAO {

    @Override
    public Model findByName(String name) {
        Model model = null;
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("model.with.name");
        query.setParameter("name", name);
        model = (Model) query.uniqueResult();
        return model;
    }

    @Override
    public List<Model> findByBrand(Brand brand) {
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("model.with.brand");
        query.setParameter("brand", brand);
        List<Model> models = query.list();
        return models;
    }
}
