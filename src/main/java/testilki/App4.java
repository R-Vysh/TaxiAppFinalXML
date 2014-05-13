package testilki;

import org.hibernate.Session;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Favourite;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.repository.hibernate.FavouriteHibernateDAO;
import util.HibernateUtil;

import java.util.List;

public class App4 {

    public static void main(String[] args) {

        Session session;
        session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        
        User user = new User();
        user.setMobile("+380993894569");
        user.setPassword("14527");
        user.setTaxist(Boolean.FALSE);
        session.save(user);
        
        Customer cust1 = new Customer();
        cust1.setBlames(0);
        cust1.setUser(user);
        cust1.setCustomerId(user.getUserId());
        session.save(cust1);
        
        Favourite fav = new Favourite();
        fav.setAddress("Kyiv, Khreshchatick str, 19");
        session.save(fav);
        
        Favourite fav2 = new Favourite();
        fav2.setAddress("Kyiv, Grushevskogo str, 19");
        session.save(fav2);
        
        FavouriteHibernateDAO favDao = new FavouriteHibernateDAO();
        List<Favourite> favs = favDao.findByCustomer(cust1);
        
        session.getTransaction().commit();
        session.close();
        for(Favourite fv : favs) {
            System.out.println(fv.getAddress().toString());
        }
        
        System.out.println("Done");
        
    }
}
