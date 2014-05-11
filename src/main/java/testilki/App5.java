package testilki;

import org.hibernate.Session;
import ua.ros.taxiapp.domain.Comment;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Order;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.repository.hibernate.CustomerHibernateDAO;
import ua.ros.taxiapp.repository.hibernate.TaxistHibernateDAO;
import util.HibernateUtil;

public class App5 {

    public static void main(String[] args) {

        Session session;
        CustomerHibernateDAO cDAO = new CustomerHibernateDAO();
        TaxistHibernateDAO tDAO = new TaxistHibernateDAO();
        
        session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        
        
        Comment comment = new Comment();
        comment.setCustomer(cDAO.findByID(Customer.class, 22));
        comment.setTaxist(tDAO.findByID(Taxist.class, 23));
        comment.setRating(5);
        comment.setText("Cool guy");
        session.save(comment);
        
        Order ord = new Order();
        ord.setCustomer(cDAO.findByID(Customer.class, 22));
        ord.setFromPlace("Vokzal");
        ord.setToPlace("Airport");
        ord.setPrice(125.0);
        ord.setStatus("NotDone");
        ord.setTaxist(tDAO.findByID(Taxist.class, 23));
        session.save(ord);
        
        
        session.getTransaction().commit();
        session.close();
        
        System.out.println("Done");
        
    }
}
