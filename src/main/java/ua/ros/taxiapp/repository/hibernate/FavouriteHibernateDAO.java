package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Favourite;
import ua.ros.taxiapp.repository.FavouriteDAO;

import java.util.List;

@Repository
public class FavouriteHibernateDAO extends GenericDAOHibernate<Favourite, Integer> implements FavouriteDAO {

    @Override
    @Transactional
    public Favourite findByAddress(String address) {
        Favourite fav = null;
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("favourite.with.address");
        query.setParameter("address", address);
        fav = (Favourite) query.uniqueResult();
        return fav;
    }
}
