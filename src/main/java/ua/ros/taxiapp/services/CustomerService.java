package ua.ros.taxiapp.services;

import java.util.List;
import ua.ros.taxiapp.domain.Customer;

public interface CustomerService {

    public boolean createCustomer(Customer customer);

    public Customer findCustomerByMobile(String mobile);

    public Customer findCustomerById(Integer id);

    public List<Customer> getAllCustomers();
}
