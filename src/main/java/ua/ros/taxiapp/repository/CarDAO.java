package ua.ros.taxiapp.repository;

import ua.ros.taxiapp.domain.Car;

import java.util.List;

public interface CarDAO extends GenericDAO<Car, Integer> {

    public Car findByNumber(String number);

    public List<Car> findByPricePerKm(Double price);

    public List<Car> criteriaSearch(String brand, String model, Double pricePerKmLow, Double pricePerKmHigh);

    public List<String> findAllModels();

    public List<String> findAllBrands();
}
