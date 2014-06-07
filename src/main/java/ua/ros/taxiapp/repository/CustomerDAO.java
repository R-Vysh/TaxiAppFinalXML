package ua.ros.taxiapp.repository;

import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.domain.User;

public interface CustomerDAO extends GenericDAO<Customer, Integer> {

    public Customer findByCurrentOrder(Order order);

    public Customer findByMobile(String mobile);

  //  Customer findByUsernameAndPassword(String username, String password);

    public Customer findByUser(User user);


}
