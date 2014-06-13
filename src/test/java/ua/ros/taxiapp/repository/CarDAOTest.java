package ua.ros.taxiapp.repository;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.ros.taxiapp.domain.Car;

import java.util.List;

public class CarDAOTest extends RepositoryTestTemplate {

    @Autowired
    private CarDAO carDAO;

    public CarDAOTest() {
    }

    @Before
    public void setUp() {
        jdbcTemplate.execute("TRUNCATE TABLE cars");
    }

    @After
    public void tearDown() {
    }

    @Test
    @Transactional
    public void testCreateNoExceptions() {
        Car car = new Car();
        car.setBrand("Dacia");
        car.setModel("Logan");
        car.setPricePerKm(3.3);
        car.setRegistrationalNumber("AA1223AA");
        car.setYear(2008);
        carDAO.save(car);
        int size = jdbcTemplate.queryForObject("select count(*) from cars", Integer.class);
        Assert.assertEquals(1, size);
    }

    @Test(expected=DataException.class)
    @Transactional
    public void testCreateWrongRegNumber() {
        Car car = new Car();
        car.setBrand("Dacia");
        car.setModel("Logan");
        car.setPricePerKm(3.3);
        car.setRegistrationalNumber("AA1223AAFG");
        car.setYear(2008);
        carDAO.save(car);
    }

    @Test(expected=ConstraintViolationException.class)
    @Transactional
    public void testCreateEqualRegNumbers() {
        Car car = new Car();
        car.setBrand("Dacia");
        car.setModel("Logan");
        car.setPricePerKm(3.3);
        car.setRegistrationalNumber("AA1223AA");
        car.setYear(2008);
        carDAO.save(car);
        Car car2 = new Car();
        car2.setBrand("Renault");
        car2.setModel("Megane");
        car2.setPricePerKm(3.3);
        car2.setRegistrationalNumber("AA1223AA");
        car2.setYear(2008);
        carDAO.save(car2);
    }

    @Test
    @Transactional
    public void testFindByNumber() throws Exception {
        Car car = new Car();
        car.setBrand("Dacia");
        car.setModel("Logan");
        car.setPricePerKm(3.3);
        car.setRegistrationalNumber("AA1223AA");
        car.setYear(2008);
        carDAO.save(car);
        Assert.assertEquals(carDAO.findByNumber("AA1223AA"), car);
    }

    @Test
    @Transactional
    public void testFindByPricePerKm() throws Exception {
        Car car = new Car();
        car.setBrand("Dacia");
        car.setModel("Logan");
        car.setPricePerKm(3.3);
        car.setRegistrationalNumber("AA1223AA");
        car.setYear(2008);
        carDAO.save(car);
        Car car2 = new Car();
        car2.setBrand("Dacia");
        car2.setModel("Logan");
        car2.setPricePerKm(3.2);
        car2.setRegistrationalNumber("AA1223EE");
        car2.setYear(2009);
        carDAO.save(car2);
        Assert.assertEquals(carDAO.findByPricePerKm(3.2).size(), 1);
    }

    @Test
    @Transactional
    public void testCriteriaSearch() throws Exception {
        Car car = new Car();
        car.setBrand("Renault");
        car.setModel("Logan");
        car.setPricePerKm(3.3);
        car.setRegistrationalNumber("AA1223AA");
        car.setYear(2008);
        carDAO.save(car);
        Car car2 = new Car();
        car2.setBrand("Dacia");
        car2.setModel("Logan");
        car2.setPricePerKm(3.2);
        car2.setRegistrationalNumber("AA1223EE");
        car2.setYear(2009);
        carDAO.save(car2);
        Car car3 = new Car();
        car3.setBrand("Renault");
        car3.setModel("Megane");
        car3.setPricePerKm(3.5);
        car3.setRegistrationalNumber("AA1223AX");
        car3.setYear(2009);
        carDAO.save(car3);
        Assert.assertEquals(carDAO.criteriaSearch("Renault", null, null, null).size(), 2);
        Assert.assertEquals(carDAO.criteriaSearch(null, "Logan", null, null).size(), 2);
        Assert.assertEquals(carDAO.criteriaSearch(null,null,3.3,null).size(), 2);
        Assert.assertEquals(carDAO.criteriaSearch(null,null,null,3.25).size(), 1);
    }

    @Test
    @Transactional
    public void testFindAllCars() {
        Car car = new Car();
        car.setBrand("Renault");
        car.setModel("Logan");
        car.setPricePerKm(3.3);
        car.setRegistrationalNumber("AA1223AA");
        car.setYear(2008);
        carDAO.save(car);
        Car car2 = new Car();
        car2.setBrand("Dacia");
        car2.setModel("Logan");
        car2.setPricePerKm(3.2);
        car2.setRegistrationalNumber("AA1223EE");
        car2.setYear(2009);
        carDAO.save(car2);
        Car car3 = new Car();
        car3.setBrand("Renault");
        car3.setModel("Megane");
        car3.setPricePerKm(3.5);
        car3.setRegistrationalNumber("AA1223AX");
        car3.setYear(2009);
        carDAO.save(car3);
        List<Car> actualResult = carDAO.findAll(Car.class);
        Assert.assertEquals(3, actualResult.size());
    }
}
