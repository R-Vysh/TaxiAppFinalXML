package ua.ros.taxiapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ua.ros.taxiapp.domain.Authority;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.repository.CustomerDAO;

import java.util.HashSet;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDAO customerDAO;

    public CustomerServiceImpl() {
    }

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    @Override
    public boolean createCustomer(Customer customer) {
        customer.getUser().setTaxist(false);
        Authority authority = new Authority();
        authority.setRolename(Authority.Rolename.ROLE_CUST);
        authority.setUser(customer.getUser());
        HashSet<Authority> auth = new HashSet<>();
        auth.add(authority);
        customer.getUser().setAuthorities(auth);
        return saveCustomer(customer);
    }

    @Override
    public boolean saveCustomer(Customer customer) {
        try {
            customerDAO.save(customer);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public Customer findById(Integer id) {
        return customerDAO.findByID(Customer.class, id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.findAll(Customer.class);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        try {
            customerDAO.update(customer);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCustomer(Customer customer) {
        try {
            customerDAO.delete(customer);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public Customer findByUser(User user) {
        return customerDAO.findByUser(user);
    }

    @Override
    public boolean cancelOrder(Customer customer) {
        customer.setCurrentOrder(null);
        return updateCustomer(customer);
    }

    @Override
    public Customer findByUsernameAndPassword(String username, String password) {
        return customerDAO.findByUsernameAndPassword(username, password);
    }

    @Override
    public Customer findByMobile(String mobile) {
        return customerDAO.findByMobile(mobile);
    }
}
