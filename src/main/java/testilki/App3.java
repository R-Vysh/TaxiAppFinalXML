package testilki;

import org.hibernate.Session;
import ua.ros.taxiapp.domain.*;
import util.HibernateUtil;

public class App3 {

    public static void main(String[] args) {

        Session session;
        session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        
        User user = new User();
        user.setMobile("+380991234569");
        user.setPassword("1237");
        user.setTaxist(Boolean.FALSE);
        session.save(user);
        
        Customer cust1 = new Customer();
        cust1.setBlames(0);
        cust1.setUser(user);
        cust1.setCustomerId(user.getUserId());
        session.save(cust1);
        
        User user2 = new User();
        user2.setMobile("+380991234570");
        user2.setPassword("123457");
        user2.setTaxist(Boolean.TRUE);
        session.save(user2);
        
        Brand brand  = new Brand();
        brand.setBrandsName("Peugeot");
        session.save(brand);
        
        Model model = new Model();
        model.setBrand(brand);
        model.setModelsName("308");
        session.save(model);
        
        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setPricePerKm(8.2);
        car.setRegNumber("AA1267HX");
        car.setYear(2006);
        session.save(car);
        
        Taxist taxist = new Taxist();
        taxist.setCar(car);
        taxist.setFree(Boolean.TRUE);
        taxist.setRating(0.0);
        taxist.setUser(user2);
        taxist.setTaxistId(user2.getUserId());
        session.save(taxist);
        
        session.getTransaction().commit();
        System.out.println("Done");
        session.close();
    }
}
