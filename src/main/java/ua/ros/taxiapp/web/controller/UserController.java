package ua.ros.taxiapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/rest/users")
public class UserController {

    @Autowired
    UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User findUserById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    @ResponseBody
    public User findUserByPasswordAndUsername(@RequestParam("password") String password, @RequestParam String username) {
        return userService.findByPasswordAndUsername(password, username);
    }

}
