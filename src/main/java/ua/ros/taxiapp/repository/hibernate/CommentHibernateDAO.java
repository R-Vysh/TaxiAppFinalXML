package ua.ros.taxiapp.repository.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import ua.ros.taxiapp.domain.Comment;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.repository.CommentDAO;

import java.util.List;

@Repository
public class CommentHibernateDAO extends GenericDAOHibernate<Comment, Integer> implements CommentDAO {

    @Override
    public List<Comment> findByCustomer(Customer customer) {
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("comment.with.customer");
        query.setParameter("customer", customer);
        List<Comment> comments = (List<Comment>) query.list();
        return comments;
    }

    @Override
    public List<Comment> findByTaxist(Taxist taxist) {
        Query query = this.getSessionFactory().getCurrentSession().getNamedQuery("comment.with.taxist");
        query.setParameter("taxist", taxist);
        List<Comment> comments = (List<Comment>) query.list();
        return comments;
    }
}
