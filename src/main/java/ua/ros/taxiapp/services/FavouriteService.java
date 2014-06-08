package ua.ros.taxiapp.services;

import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Favourite;

import java.util.List;

public interface FavouriteService {
    public boolean saveFavourite(Favourite favourite);

    public Favourite findById(Integer id);

    public List<Favourite> getAllFavourites();

    public boolean deleteFavourite(Favourite favourite);

    public boolean updateFavourite(Favourite favourite);
}
