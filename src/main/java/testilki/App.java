package testilki;

import org.hibernate.Session;
import ua.ros.taxiapp.domain.Brand;
import ua.ros.taxiapp.domain.Model;
import util.HibernateUtil;

public class App {

    public static void main(String[] args) {

        Session session;
        session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Brand brand = new Brand();
        brand.setBrandsName("Toyota");
        session.save(brand);

        Model model = new Model();
        model.setModelsName("Avensis");
        model.setBrand(brand);
        brand.getModels().add(model);

        session.save(model);

        session.getTransaction().commit();
        System.out.println("Done");
        System.out.println(brand.getModels());
        session.close();
    }
}
