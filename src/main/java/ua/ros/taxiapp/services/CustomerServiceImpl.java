package ua.ros.taxiapp.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.repository.CustomerDAO;

public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    CustomerDAO customerDAO;
    
    public CustomerServiceImpl() {
        
    }
    
    @Override
    public boolean createCustomer(Customer customer) {
        customerDAO.save(customer);
        return true;
    }

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    @Override
    public Customer findCustomerByMobile(String mobile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer findCustomerById(Integer id) {
        return customerDAO.findByID(Customer.class, id);
    }
    
    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.findAll(Customer.class);
    }
}
