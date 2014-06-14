package ua.ros.taxiapp.services;

import ua.ros.taxiapp.domain.Car;

import java.util.List;

public interface CarService {
    public boolean createCar(Car car);

    public Car findById(Integer id);

    public List<Car> getAllCars();

    public Car findByNumber(String number);

    public boolean updateCar(Car car);

    public boolean deleteCar(Car car);

    public List<String> allBrands();

    public List<String> allModels();

    public List<Car> findWithCriteria(String modelName, String brandName, Double pricePerKmHigh, Double pricePerKmLow);
}
