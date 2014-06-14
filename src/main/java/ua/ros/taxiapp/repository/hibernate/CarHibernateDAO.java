package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.ros.taxiapp.domain.Car;
import ua.ros.taxiapp.repository.CarDAO;

import java.util.List;

@Repository
public class CarHibernateDAO extends GenericDAOHibernate<Car, Integer> implements CarDAO {

    @Override
    @Transactional
    public Car findByNumber(String registrationalNumber) {
        Car car = null;
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("car.with.number");
        query.setParameter("regNumber", registrationalNumber);
        car = (Car) query.uniqueResult();
        return car;
    }

    @Override
    @Transactional
    public List<Car> findByPricePerKm(Double price) {
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("car.with.price");
        query.setParameter("price", price);
        List<Car> cars = (List<Car>) query.list();
        return cars;
    }

    @Override
    @Transactional
    public List<Car> criteriaSearch(String brand, String model, Double pricePerKmLow, Double pricePerKmHigh) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Car.class);
        if (model != null && !model.isEmpty()) {
            criteria.add(Restrictions.like("model", model));
        }
        if (brand != null && !brand.isEmpty()) {
            criteria.add(Restrictions.like("brand", brand));
        }
        if (pricePerKmLow != null) {
            criteria.add(Restrictions.ge("pricePerKm", pricePerKmLow));
        }
        if (pricePerKmHigh != null) {
            criteria.add(Restrictions.le("pricePerKm", pricePerKmHigh));
        }
        return criteria.list();
    }

    @Override
    @Transactional
    public List<String> findAllModels() {
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("all.models");
        List<String> models = (List<String>) query.list();
        return models;
    }

    @Override
    @Transactional
    public List<String> findAllBrands() {
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("all.brands");
        List<String> brands = (List<String>) query.list();
        return brands;
    }
}
