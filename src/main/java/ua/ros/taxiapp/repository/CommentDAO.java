package ua.ros.taxiapp.repository;

import java.util.List;
import ua.ros.taxiapp.domain.Comment;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Taxist;

public interface CommentDAO extends GenericDAO<Comment, Integer> {

    public List<Comment> findByCustomer(Customer customer);

    public List<Comment> findByTaxist(Taxist taxist);
}
