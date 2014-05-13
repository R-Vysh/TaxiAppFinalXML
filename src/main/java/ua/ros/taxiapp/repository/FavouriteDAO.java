package ua.ros.taxiapp.repository;

import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Favourite;

import java.util.List;

public interface FavouriteDAO extends GenericDAO<Favourite, Integer> {

    public Favourite findByAddress(String address);

    public List<Favourite> findByCustomer(Customer customer);
}
