package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Favourite;
import ua.ros.taxiapp.repository.FavouriteDAO;

import java.util.List;

@Repository
public class FavouriteHibernateDAO extends GenericDAOHibernate<Favourite, Integer> implements FavouriteDAO {
    
    @Override
    public Favourite findByAddress(String address) {
        Favourite fav = null;
        Query query = this.getSession().getNamedQuery("favourite.with.address");
        query.setParameter("address", address);
        fav = findOne(query);
        return fav;
    }
}
