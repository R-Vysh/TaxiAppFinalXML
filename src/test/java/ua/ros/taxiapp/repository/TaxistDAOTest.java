package ua.ros.taxiapp.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.ros.taxiapp.domain.Car;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.domain.User;

import javax.validation.ConstraintViolationException;
import java.util.List;

public class TaxistDAOTest extends RepositoryTestTemplate {

    @Autowired
    private TaxistDAO taxistDAO;

    public TaxistDAOTest() {
    }

    @Before
    public void setUp() {
        jdbcTemplate.execute("TRUNCATE TABLE taxists");
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
        User user = new User(null, "123", "+380971234567", true, null, "John", null);
        Taxist taxist = new Taxist(car, user);
        taxist.setOnline(false);
        taxist.setFree(false);
        taxistDAO.save(taxist);
        int size = jdbcTemplate.queryForObject("select count(*) from taxists", Integer.class);
        Assert.assertEquals(1, size);
    }

    @Test(expected=ConstraintViolationException.class)
    @Transactional
    public void testCreateNoUser() {
        Car car = new Car();
        car.setBrand("Dacia");
        car.setModel("Logan");
        car.setPricePerKm(3.3);
        car.setRegistrationalNumber("AA1223AA");
        car.setYear(2008);
        Taxist taxist = new Taxist();
        taxist.setCar(car);
        taxist.setOnline(false);
        taxist.setFree(false);
        taxistDAO.save(taxist);
    }

    @Test(expected=ConstraintViolationException.class)
    @Transactional
    public void testCreateNoCar() {
        Taxist taxist = new Taxist();
        User user = new User(null, "123", "+380971234567", true, null, "John", null);
        taxist.setUser(user);
        taxist.setOnline(false);
        taxist.setFree(false);
        taxistDAO.save(taxist);
    }

    @Test
    @Transactional
    public void testFindByMobile() throws Exception {
        Car car = new Car();
        car.setBrand("Dacia");
        car.setModel("Logan");
        car.setPricePerKm(3.3);
        car.setRegistrationalNumber("AA1223AA");
        car.setYear(2008);
        User user = new User(null, "123", "+380971234567", true, null, "John", null);
        Taxist taxist = new Taxist(car, user);
        taxist.setOnline(false);
        taxist.setFree(false);
        taxistDAO.save(taxist);
        Taxist result = taxistDAO.findByMobile("+380971234567");
        Assert.assertEquals(result, taxist);
    }

    @Test
    @Transactional
    public void testFindAllFree() throws Exception {
        Car car = new Car();
        car.setBrand("Dacia");
        car.setModel("Logan");
        car.setPricePerKm(3.3);
        car.setRegistrationalNumber("AA1223AA");
        car.setYear(2008);
        User user = new User(null, "123", "+380971234567", true, null, "John", null);
        Taxist taxist = new Taxist(car, user);
        taxist.setOnline(false);
        taxist.setFree(false);
        taxistDAO.save(taxist);
        Car car2 = new Car();
        car2.setBrand("Dacia");
        car2.setModel("Logan");
        car2.setPricePerKm(3.3);
        car2.setRegistrationalNumber("AA1223AX");
        car2.setYear(2008);
        User user2 = new User(null, "123", "+380971234569", true, null, "Johny", null);
        Taxist taxist2 = new Taxist(car2, user2);
        taxist2.setOnline(true);
        taxist2.setFree(true);
        taxistDAO.save(taxist2);
        List<Taxist> result = taxistDAO.findAllFree();
        Assert.assertEquals(1, result.size());
    }

    @Test
    @Transactional
    public void testFindByUser() throws Exception {
        Car car = new Car();
        car.setBrand("Dacia");
        car.setModel("Logan");
        car.setPricePerKm(3.3);
        car.setRegistrationalNumber("AA1223AA");
        car.setYear(2008);
        User user = new User(null, "123", "+380971234567", true, null, "John", null);
        Taxist taxist = new Taxist(car, user);
        taxist.setOnline(false);
        taxist.setFree(false);
        taxistDAO.save(taxist);
        Taxist result = taxistDAO.findByUser(user);
        Assert.assertEquals(taxist, result);
    }
}
