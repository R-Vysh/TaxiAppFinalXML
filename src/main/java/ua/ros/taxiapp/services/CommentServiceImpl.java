package ua.ros.taxiapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ua.ros.taxiapp.domain.Comment;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.repository.CommentDAO;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDAO commentDAO;

    public CommentDAO getCommentDAO() {
        return commentDAO;
    }

    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public boolean createComment(Comment comment) {
        try {
            commentDAO.save(comment);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public Comment findById(Integer id) {
        return commentDAO.findByID(Comment.class, id);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentDAO.findAll(Comment.class);
    }

    @Override
    public boolean updateComment(Comment comment) {
        try {
            commentDAO.save(comment);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteComment(Comment comment) {
        try {
            commentDAO.delete(comment);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public List<Comment> findByTaxist(Taxist taxist) {
        return commentDAO.findByTaxist(taxist);
    }

    @Override
    public List<Comment> findByCustomer(Customer customer) {
        return commentDAO.findByCustomer(customer);
    }
}
