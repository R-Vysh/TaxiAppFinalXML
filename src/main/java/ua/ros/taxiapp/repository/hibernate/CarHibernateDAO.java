package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.ros.taxiapp.domain.Car;
import ua.ros.taxiapp.repository.CarDAO;

import java.util.List;

@Repository
public class CarHibernateDAO extends GenericDAOHibernate<Car, Integer> implements CarDAO {

    @Override
    public Car findByNumber(String registrationalNumber) {
        Car car = null;
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("car.with.number");
        query.setParameter("regNumber", registrationalNumber);
        car = (Car) query.uniqueResult();
        return car;
    }

    @Override
    public List<Car> findByPricePerKm(Double price) {
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("car.with.price");
        query.setParameter("price", price);
        List<Car> cars = (List<Car>) query.list();
        return cars;
    }

    @Override
    public List<Car> findByPricePerKmBetween(Double lower, Double higher) {
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("car.with.price.between");
        query.setParameter("lowerPrice", lower);
        query.setParameter("higherPrice", higher);
        List<Car> cars = (List<Car>) query.list();
        return cars;
    }

    @Override
    @Transactional
    public List<Car> criteriaSearch(String brand, String model, Double pricePerKmLow, Double pricePerKmHigh) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Car.class);
        if(model!=null){
            criteria.createAlias("model", "model");
            criteria.add(Restrictions.like("model.modelsName", model));
        }
        if(brand!=null){
            criteria.createAlias("model.brand", "brand");
            criteria.add(Restrictions.like("brand.brandsName", brand));
        }
        if(pricePerKmLow!=null){
            criteria.add(Restrictions.ge("pricePerKm", pricePerKmLow));
        }
        if(pricePerKmHigh!=null){
            criteria.add(Restrictions.le("pricePerKm", pricePerKmHigh));
        } 
        return criteria.list();
    }
}
