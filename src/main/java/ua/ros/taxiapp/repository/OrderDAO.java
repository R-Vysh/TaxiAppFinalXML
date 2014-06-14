package ua.ros.taxiapp.repository;

import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.domain.Taxist;

import java.util.List;

public interface OrderDAO extends GenericDAO<Order, Integer> {
    public List<Order> findActiveOrders();

    public List<Order> findByCustomer(Customer customer);

    public List<Order> findForTaxist(Taxist taxist);

    public List<Order> findByTaxist(Taxist taxist);

    public Double countIncome(Taxist taxist);

    public Double countOutcome(Customer customer);

    public List<Order> findByCustomer(Customer customer, Integer page);
}
