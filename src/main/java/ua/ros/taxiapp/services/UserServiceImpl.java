package ua.ros.taxiapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.repository.UserDAO;

import java.io.Serializable;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll(User.class);
    }

    @Override
    public User findById(Integer id) {
        return userDAO.findByID(User.class, id);
    }

    @Override
    public boolean createNewUser(User user) {
        try {
            userDAO.save(user);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteUser(User user) {
        try {
            userDAO.delete(user);
        } catch (DataAccessException ex) {
            return false;
        }
        return true;
    }
}
