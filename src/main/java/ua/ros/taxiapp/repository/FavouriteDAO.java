package ua.ros.taxiapp.repository;

import java.util.List;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Favourite;

public interface FavouriteDAO extends GenericDAO<Favourite, Integer> {

    public Favourite findByAddress(String address);

    public List<Favourite> findByCustomer(Customer customer);
}
