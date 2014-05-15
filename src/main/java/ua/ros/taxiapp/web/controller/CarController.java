package ua.ros.taxiapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.ros.taxiapp.domain.Brand;
import ua.ros.taxiapp.domain.Car;
import ua.ros.taxiapp.services.BrandService;
import ua.ros.taxiapp.services.CarService;

import java.util.List;

@Controller
@RequestMapping("/rest/cars")
public class CarController {
    // private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    private CarService carService;

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Car findCarById(@PathVariable("id") Integer id) {
        return carService.findById(id);
    }
}
