package ua.ros.taxiapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.repository.CustomerDAO;

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
        try {
            customerDAO.save(customer);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Secured(value = "ROLE_USER")
    @Override
    public Customer findById(Integer id) {
        return customerDAO.findByID(Customer.class, id);
    }

    @Secured(value = "ROLE_USER")
    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.findAll(Customer.class);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        try {
            customerDAO.save(customer);
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
    public Customer findByMobile(String mobile) {
        return customerDAO.findByMobile(mobile);
    }
}
