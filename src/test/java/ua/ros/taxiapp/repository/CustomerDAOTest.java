package ua.ros.taxiapp.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.User;

import javax.validation.ValidationException;

public class CustomerDAOTest extends RepositoryTestTemplate {

    @Autowired
    private CustomerDAO customerDAO;

    public CustomerDAOTest() {
    }

    @Before
    public void setUp() {
        jdbcTemplate.execute("TRUNCATE TABLE customers");
    }

    @After
    public void tearDown() {
    }

    @Test
    @Transactional
    public void testCreateNoExceptions() {
        Customer customer = new Customer();
        User user = new User();
        user.setPassword("123");
        user.setUsername("Johny");
        user.setTaxist(false);
        user.setMobile("+380991237812");
        customer.setUser(user);
        customerDAO.save(customer);
        int size = jdbcTemplate.queryForObject("select count(*) from customers", Integer.class);
        Assert.assertEquals(1, size);
    }

    @Test(expected = ValidationException.class)
    @Transactional
    public void testCreateNoUser() {
        Customer customer = new Customer();
        customerDAO.save(customer);
    }

    @Test
    @Transactional
    public void testFindByMobile() throws Exception {
        Customer customer = new Customer();
        User user = new User();
        user.setPassword("123");
        user.setUsername("Johny");
        user.setTaxist(false);
        user.setMobile("+380991237812");
        customer.setUser(user);
        customerDAO.save(customer);
        Customer result = customerDAO.findByMobile("+380991237812");
        Assert.assertEquals(result, customer);
    }

    @Test
    @Transactional
    public void testFindByMobileReturnsNull() throws Exception {
        Customer customer = new Customer();
        User user = new User();
        user.setPassword("123");
        user.setUsername("Johny");
        user.setTaxist(false);
        user.setMobile("+380991237812");
        customer.setUser(user);
        customerDAO.save(customer);
        Customer result = customerDAO.findByMobile("+380991237813");
        Assert.assertEquals(result, null);
    }

    @Test
    @Transactional
    public void testFindByUser() throws Exception {
        Customer customer = new Customer();
        User user = new User();
        user.setPassword("123");
        user.setUsername("Johny");
        user.setTaxist(false);
        user.setMobile("+380991237812");
        customer.setUser(user);
        customerDAO.save(customer);
        Customer result = customerDAO.findByUser(user);
        Assert.assertEquals(result, customer);
    }

    @Test
    @Transactional
    public void testFindByUsernameAndPassword() throws Exception {
        Customer customer = new Customer();
        User user = new User();
        user.setPassword("123");
        user.setUsername("Johny");
        user.setTaxist(false);
        user.setMobile("+380991237812");
        customer.setUser(user);
        customerDAO.save(customer);
        Customer result = customerDAO.findByUsernameAndPassword("Johny", "123");
        Assert.assertEquals(result, customer);
    }

    @Test
    @Transactional
    public void testFindByUsernameAndPasswordReturnsNull() throws Exception {
        Customer customer = new Customer();
        User user = new User();
        user.setPassword("123");
        user.setUsername("Johny");
        user.setTaxist(false);
        user.setMobile("+380991237812");
        customer.setUser(user);
        customerDAO.save(customer);
        Customer result = customerDAO.findByUsernameAndPassword("Johnzy","123");
        Assert.assertEquals(result, null);
    }

    @Test
    @Transactional
    public void testFindAllCustomers() {
        Customer customer = new Customer();
        User user = new User();
        user.setPassword("123");
        user.setUsername("Johny");
        user.setTaxist(false);
        user.setMobile("+380991237812");
        customer.setUser(user);
        customerDAO.save(customer);
        Customer customer2 = new Customer();
        User user2 = new User();
        user2.setPassword("123");
        user2.setUsername("John");
        user2.setTaxist(false);
        user2.setMobile("+380991237814");
        customer2.setUser(user);
        customerDAO.save(customer2);
        int size = jdbcTemplate.queryForObject("select count(*) from customers", Integer.class);
        Assert.assertEquals(2, size);
    }

}
