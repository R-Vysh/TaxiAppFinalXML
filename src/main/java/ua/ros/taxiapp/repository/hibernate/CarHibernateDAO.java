package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import ua.ros.taxiapp.domain.Brand;
import ua.ros.taxiapp.domain.Car;
import ua.ros.taxiapp.domain.Model;
import ua.ros.taxiapp.repository.CarDAO;

import java.util.List;

public class CarHibernateDAO extends GenericDAOHibernate<Car, Integer> implements CarDAO {
    
    @Override
    public Car findByNumber(String number) {
        Car car = null;
        String hql = "FROM cars WHERE REG_NUMBER = :number";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("number", number);
        car = findOne(query);
        return car;
    }
    
    @Override
    public List<Car> findByPricePerKm(Double price) {
        String hql = "FROM cars WHERE PRICE_PER_KM = :price";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("price", price);
        List<Car> cars = (List<Car>) query.list();
        return cars;
    }
    
    @Override
    public List<Car> findByPricePerKmBetween(Double lower, Double higher) {
        String hql = "FROM cars WHERE PRICE_PER_KM BETWEEN :lower AND :higher";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("lower", lower);
        query.setParameter("higher", higher);
        List<Car> cars = (List<Car>) query.list();
        return cars;
    }
    
    @Override
    public List<Car> findByBrand(Brand brand) {
        String hql = "FROM cars WHERE ID_BRAND = :brand";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("brand", brand.getBrandId());
        List<Car> cars = (List<Car>) query.list();
        return cars;
    }
    
    @Override
    public List<Car> findByBrandAndModel(Brand brand, Model model) {
        String hql = "FROM cars WHERE ID_BRAND = :brand AND ID_MODEL = :model";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("brand", brand.getBrandId());
        query.setParameter("model", model.getModelId());
        List<Car> cars = (List<Car>) query.list();
        return cars;
    }
    
    @Override
    public List<Car> findByYear(Integer year) {
        String hql = "FROM cars WHERE YEAR = :year";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("year", year);
        List<Car> cars = (List<Car>) query.list();
        return cars;
    }
}
