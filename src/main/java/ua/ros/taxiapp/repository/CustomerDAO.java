package ua.ros.taxiapp.repository;

import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.User;

public interface CustomerDAO extends GenericDAO<Customer, Integer> {

    public Customer findByMobile(String mobile);

    public Customer findByUser(User user);

    Customer findByUsernameAndPassword(String username, String password);
}
