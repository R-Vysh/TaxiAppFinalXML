package ua.ros.taxiapp.services;

import ua.ros.taxiapp.domain.Customer;

import java.util.List;

public interface CustomerService {

    public boolean createCustomer(Customer customer);

    public Customer findCustomerById(Integer id);

    public List<Customer> getAllCustomers();

    public Customer findCustomerWithMobile(String mobile);
}
