package ua.ros.taxiapp.web.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/web")
public class LoginController {

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

    @RequestMapping(value = "/user/1", method = RequestMethod.GET)
    public String sayHello() {
        return "welcome";
    }


}
