package ua.ros.taxiapp.services;

import ua.ros.taxiapp.domain.Brand;
import ua.ros.taxiapp.domain.Comment;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Taxist;

import java.util.List;

public interface CommentService {
    public boolean createComment(Comment comment);

    public Comment findById(Integer id);

    public List<Comment> getAllComments();

    public boolean updateComment(Comment comment);

    public boolean deleteComment(Comment comment);

    public List<Comment> findByTaxist(Taxist taxist);

    public List<Comment> findByCustomer(Customer customer);
}
