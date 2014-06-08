package ua.ros.taxiapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ua.ros.taxiapp.domain.Favourite;
import ua.ros.taxiapp.repository.FavouriteDAO;

import java.util.List;

@Service
public class FavouriteServiceImpl implements FavouriteService {
    @Autowired
    FavouriteDAO favouriteDAO;

    public FavouriteDAO getFavouriteDAO() {
        return favouriteDAO;
    }

    public void setFavouriteDAO(FavouriteDAO favouriteDAO) {
        this.favouriteDAO = favouriteDAO;
    }

    @Override
    public boolean saveFavourite(Favourite favourite) {
        try {
            favouriteDAO.save(favourite);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public Favourite findById(Integer id) {
        return favouriteDAO.findByID(Favourite.class, id);
    }

    @Override
    public List<Favourite> getAllFavourites() {
        return favouriteDAO.findAll(Favourite.class);
    }

    @Override
    public boolean deleteFavourite(Favourite favourite) {
        try {
            favouriteDAO.delete(favourite);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateFavourite(Favourite favourite) {
        try {
            favouriteDAO.save(favourite);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }
}
