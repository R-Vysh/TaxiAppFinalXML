package ua.ros.taxiapp.repository.hibernate;

import java.util.List;
import org.hibernate.Query;
import ua.ros.taxiapp.domain.Comment;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.repository.CommentDAO;

public class CommentHibernateDAO extends GenericDAOHibernate<Comment, Integer> implements CommentDAO {

    @Override
    public List<Comment> findByCustomer(Customer customer) {
        String hql = "FROM comments WHERE ID_CUSTOMER = :customer";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("customer", customer.getCustomerId());
        List<Comment> comments = (List<Comment>) query.list();
        return comments;
    }

    @Override
    public List<Comment> findByTaxist(Taxist taxist) {
        String hql = "FROM comments WHERE ID_TAXIST = :taxist";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("taxist", taxist.getTaxistId());
        List<Comment> comments = (List<Comment>) query.list();
        return comments;
    }
}
