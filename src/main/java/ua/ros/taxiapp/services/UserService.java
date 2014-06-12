package ua.ros.taxiapp.services;

import ua.ros.taxiapp.domain.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public User findById(Integer id);

    public boolean createNewUser(User user);

    public boolean deleteUser(User user);

    public User findByPasswordAndUsername(String password, String username);

    public User findByUsername(String username);
}
