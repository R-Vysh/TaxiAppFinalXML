package ua.ros.taxiapp.web.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;


@Controller
@RequestMapping("/web")
public class LoginWebController {
    private static final Logger logger = LoggerFactory.getLogger(MainWebController.class);

    @RequestMapping("/perform-login")
    public void doLogin(Principal principal, HttpSession session, Model model) {
    }

    @RequestMapping("/login")
    public String login(Model model,
                        @RequestParam(value = "registrationSuccessful", required = false) Boolean regSuccess) {
        if (regSuccess == Boolean.TRUE) {
          model.addAttribute("registrationSuccessful", true);
        }
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sayHello() {
        return "welcome";
    }
}
