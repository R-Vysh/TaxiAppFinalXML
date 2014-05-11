package ua.ros.taxiapp.web.controller.fixtures;

import ua.ros.taxiapp.domain.Customer;

public class RestFixtures {

    public static Customer customerNotFound(Integer key) {
        return new Customer();
    }

    public static Boolean customerCreated(Customer customer) {
        return true;
    }

    public static Boolean customerDeleted(Customer customer) {
        return true;
    }

    public static Boolean customerNotDeleted(Customer customer) {
        return false;
    }
}
