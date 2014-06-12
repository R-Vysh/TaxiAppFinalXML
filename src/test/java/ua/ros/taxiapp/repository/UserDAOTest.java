package ua.ros.taxiapp.repository;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.ros.taxiapp.domain.User;

import java.util.List;

public class UserDAOTest extends RepositoryTestTemplate {

    @Autowired
    UserDAO userDAO;

    public UserDAOTest() {
    }

    @Before
    public void setUp() {
        jdbcTemplate.execute("TRUNCATE TABLE users");
    }

    @After
    public void tearDown() {
    }

    @Test
    @Transactional
    public void testCreateNoExceptions() {
        User user = new User();
        user.setUsername("Nick");
        user.setMobile("+380991234567");
        user.setTaxist(false);
        user.setPassword("123");
        userDAO.save(user);
        int size = jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
        Assert.assertEquals(1, size);
    }

    @Test(expected=javax.validation.ConstraintViolationException.class)
    @Transactional
    public void testCreateNullField() {
        User user = new User();
        user.setUsername("Nick");
        user.setMobile("+380991234567");
        user.setTaxist(false);
        userDAO.save(user);
    }

    @Test(expected=ConstraintViolationException.class)
    @Transactional
    public void testCreateEqualUsernames() {
        User user = new User();
        user.setUsername("Nick");
        user.setMobile("+380991234567");
        user.setTaxist(false);
        user.setPassword("123");
        userDAO.save(user);
        User user2 = new User();
        user2.setUsername("Nick");
        user2.setMobile("+380991234568");
        user2.setTaxist(false);
        user2.setPassword("123");
        userDAO.save(user2);
    }

    @Test(expected=ConstraintViolationException.class)
    @Transactional
    public void testCreateEqualMobiles() {
        User user = new User();
        user.setUsername("Nickolas");
        user.setMobile("+380991234567");
        user.setTaxist(false);
        user.setPassword("123");
        userDAO.save(user);
        User user2 = new User();
        user2.setUsername("Nick");
        user2.setMobile("+380991234567");
        user2.setTaxist(false);
        user2.setPassword("123");
        userDAO.save(user2);
    }

    @Test
    @Transactional
    public void testFindAllUsers() {
        User user = new User();
        user.setUsername("Nickolas");
        user.setMobile("+380991234567");
        user.setTaxist(false);
        user.setPassword("123");
        userDAO.save(user);
        User user2 = new User();
        user2.setUsername("Nick");
        user2.setMobile("+380991234568");
        user2.setTaxist(false);
        user2.setPassword("123");
        userDAO.save(user2);
        User user3 = new User();
        user3.setUsername("Jack");
        user3.setMobile("+380991234569");
        user3.setTaxist(true);
        user3.setPassword("1234");
        userDAO.save(user3);
        List<User> actualResult = userDAO.findAll(User.class);
        Assert.assertEquals(3, actualResult.size());
    }

    @Test
    @Transactional
    public void testFindByMobile() throws Exception {
        User user = new User();
        user.setUsername("Nickolas");
        user.setMobile("+380991234567");
        user.setTaxist(false);
        user.setPassword("123");
        userDAO.save(user);
        User result = userDAO.findByMobile("+380991234567");
        Assert.assertEquals(user, result);
    }

    @Test
    @Transactional
    public void testFindByPasswordAndUsername() throws Exception {
        User user = new User();
        user.setUsername("Nickolas");
        user.setMobile("+380991234567");
        user.setTaxist(false);
        user.setPassword("123");
        userDAO.save(user);
        User result = userDAO.findByPasswordAndUsername("123","Nickolas");
        Assert.assertEquals(user, result);
    }

    @Test
    @Transactional
    public void testFindByUsername() throws Exception {
        User user = new User();
        user.setUsername("Nickolas");
        user.setMobile("+380991234567");
        user.setTaxist(false);
        user.setPassword("123");
        userDAO.save(user);
        User result = userDAO.findByUsername("Nickolas");
        Assert.assertEquals(user, result);
    }
}