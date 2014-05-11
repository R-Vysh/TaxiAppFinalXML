package ua.ros.taxiapp.repository.hibernate;

import java.util.List;
import org.hibernate.Query;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Favourite;
import ua.ros.taxiapp.repository.FavouriteDAO;

public class FavouriteHibernateDAO extends GenericDAOHibernate<Favourite, Integer> implements FavouriteDAO {
    
    @Override
    public Favourite findByAddress(String address) {
        Favourite fav = null;
        String hql = "FROM favourites WHERE ADDRESS = :addr";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("addr", address);
        fav = findOne(query);
        return fav;
    }

    @Override
    public List<Favourite> findByCustomer(Customer customer) {
        String hql = "from Favourite f join f.customers c where c.customerId=:custId";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("custId", customer.getCustomerId());
        List<Favourite> favs = (List<Favourite>) query.list();
        return favs;
    }
}
