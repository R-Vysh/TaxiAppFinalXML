package ua.ros.taxiapp.services;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.repository.UserDAO;

public class UserServiceImpl implements UserService {
    
    @Autowired
    UserDAO userDAO;
    
    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll(User.class);
    }

    @Override
    public User requestUser(Serializable id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createNewUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteUser(Serializable id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
