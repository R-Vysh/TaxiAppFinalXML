package com.yummynoodlebar.rest.controller.fixture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.domain.User;

public class RestDataFixtures {

    public static final String MOBILE = "+380967777777";
    public static final String PASSWORD = "qwe123";

    public static List<Customer> allCustomers() {
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(standardCustomer());
        customers.add(standardCustomer());
        customers.add(standardCustomer());
        return customers;
    }

    public static Customer standardCustomer() {
        Customer cust = new Customer();
        User user = new User();
        user.setMobile(MOBILE);
        user.setTaxist(Boolean.FALSE);
        user.setPassword(PASSWORD);
        cust.setUser(user);
        return cust;
    }

    public static String standardCustomerJSON() {
        return "{ \"items\": { \"yummy1\": 12, \"yummy15\": 42 } }";
    }
}
