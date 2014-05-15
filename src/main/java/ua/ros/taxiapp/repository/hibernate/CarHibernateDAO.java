package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import ua.ros.taxiapp.domain.Car;
import ua.ros.taxiapp.repository.CarDAO;

import java.util.List;

@Repository
public class CarHibernateDAO extends GenericDAOHibernate<Car, Integer> implements CarDAO {

    @Override
    public Car findByNumber(String registrationalNumber) {
        Car car = null;
        Query query = this.getSession().getNamedQuery("car.with.number");
        query.setParameter("regNumber", registrationalNumber);
        car = findOne(query);
        return car;
    }

    @Override
    public List<Car> findByPricePerKm(Double price) {
        Query query = this.getSession().getNamedQuery("car.with.price");
        query.setParameter("price", price);
        List<Car> cars = (List<Car>) query.list();
        return cars;
    }

    @Override
    public List<Car> findByPricePerKmBetween(Double lower, Double higher) {
        Query query = this.getSession().getNamedQuery("car.with.price.between");
        query.setParameter("lowerPrice", lower);
        query.setParameter("higherPrice", higher);
        List<Car> cars = (List<Car>) query.list();
        return cars;
    }

    //TODO Criteria API !!!

//    @Override
//    public List<Car> findByBrand(Brand brand) {
//        String hql = "FROM cars WHERE ID_BRAND = :brand";
//        Query query = this.getSession().createQuery(hql);
//        query.setParameter("brand", brand.getBrandId());
//        List<Car> cars = (List<Car>) query.list();
//        return cars;
//    }
//
//    @Override
//    public List<Car> findByBrandAndModel(Brand brand, Model model) {
//        String hql = "FROM cars WHERE ID_BRAND = :brand AND ID_MODEL = :model";
//        Query query = this.getSession().createQuery(hql);
//        query.setParameter("brand", brand.getBrandId());
//        query.setParameter("model", model.getModelId());
//        List<Car> cars = (List<Car>) query.list();
//        return cars;
//    }
//
//    @Override
//    public List<Car> findByYear(Integer year) {
//        String hql = "FROM cars WHERE YEAR = :year";
//        Query query = this.getSession().createQuery(hql);
//        query.setParameter("year", year);
//        List<Car> cars = (List<Car>) query.list();
//        return cars;
//    }
}
