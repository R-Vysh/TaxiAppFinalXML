package ua.ros.taxiapp.web.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.services.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/web")
public class MainWebController {
    @Autowired
    UserService userService;

    @RequestMapping("/main")
    public String mainMenu(Principal principal) {
        String username = principal.getName();
        System.out.println(username);
        User user = userService.findById(40);
        System.out.println(user.getMobile());
        return user.getUsername();
    }
}
