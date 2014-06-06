package ua.ros.taxiapp.web.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;


@Controller
@RequestMapping("/web")
public class LoginWebController {
    private static final Logger logger = LoggerFactory.getLogger(MainWebController.class);

    @RequestMapping("/perform-login")
    public void doLogin(Principal principal, HttpSession session, Model model) {
        logger.info("logged in");
        System.out.println("we are here");
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

//    @RequestMapping(value = "/perform-login", method = RequestMethod.GET)
//    public void doLogin(@RequestParam("j_password") String j_password,
//                        @RequestParam("j_username") String j_username, HttpSession session) {
//    }

    @RequestMapping(value = "/change-password", method = RequestMethod.GET)
    public String changePassword() {
        return "changePassword";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sayHello() {
        return "welcome";
    }


}
