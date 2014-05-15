package ua.ros.taxiapp.repository;

import ua.ros.taxiapp.domain.Comment;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Taxist;

import java.util.List;

public interface CommentDAO extends GenericDAO<Comment, Integer> {

    public List<Comment> findByCustomer(Customer customer);

    public List<Comment> findByTaxist(Taxist taxist);

}
