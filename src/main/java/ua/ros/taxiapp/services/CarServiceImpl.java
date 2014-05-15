package ua.ros.taxiapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ua.ros.taxiapp.domain.Car;
import ua.ros.taxiapp.repository.CarDAO;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarDAO carDAO;

    public CarDAO getCarDAO() {
        return carDAO;
    }

    public void setCarDAO(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    public boolean createCar(Car car) {
        try {
            carDAO.save(car);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public Car findById(Integer id) {
        return carDAO.findByID(Car.class, id);
    }

    @Override
    public List<Car> getAllCars() {
        return carDAO.findAll(Car.class);
    }

    @Override
    public Car findByNumber(String number) {
        return carDAO.findByNumber(number);
    }

    @Override
    public boolean updateCar(Car car) {
        try {
            carDAO.save(car);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCar(Car car) {
        try {
            carDAO.delete(car);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }
}
