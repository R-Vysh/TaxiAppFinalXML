package testilki;

import org.hibernate.Session;
import ua.ros.taxiapp.domain.Brand;
import ua.ros.taxiapp.domain.Car;
import ua.ros.taxiapp.domain.Model;
import util.HibernateUtil;

public class App2 {

    public static void main(String[] args) {

        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Brand brand = new Brand();
        brand.setBrandsName("Ford");
        session.save(brand);

        Model model = new Model();
        model.setModelsName("Fiesta");
        model.setBrand(brand);
        brand.getModels().add(model);
        session.save(model);
        
        Car car = new Car();
        car.setModel(model);
        car.setPricePerKm(8.1);
        car.setRegNumber("AA7878HE");
        car.setYear(2009);
        session.save(car);
        
        session.getTransaction().commit();
        System.out.println("Done");
        session.close();
    }
}
