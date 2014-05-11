package ua.ros.taxiapp.services;

import java.io.Serializable;
import java.util.List;
import ua.ros.taxiapp.domain.User;

public interface UserService {

    public List<User> getAllUsers();

    public User requestUser(Serializable id);

    public boolean createNewUser(User user);

    public boolean deleteUser(Serializable id);
}
