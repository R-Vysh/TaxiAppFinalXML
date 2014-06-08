package ua.ros.taxiapp.services;

import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.User;

import java.util.List;

public interface CustomerService {

    public boolean createCustomer(Customer customer);

    public boolean saveCustomer(Customer customer);

    public Customer findById(Integer id);

    public List<Customer> getAllCustomers();

    public Customer findByMobile(String mobile);

    public boolean updateCustomer(Customer customer);

    public boolean deleteCustomer(Customer customer);

    public Customer findByUser(User user);

    public boolean cancelOrder(Customer customer);

    public Customer findByUsernameAndPassword(String username, String password);
}
