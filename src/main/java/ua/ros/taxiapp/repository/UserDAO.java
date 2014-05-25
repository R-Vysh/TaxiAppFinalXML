package ua.ros.taxiapp.repository;

import ua.ros.taxiapp.domain.User;

public interface UserDAO extends GenericDAO<User, Integer> {
    public User findByMobile(String mobile);

    User findByPasswordAndUsername(String password, String username);
}
