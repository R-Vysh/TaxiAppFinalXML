package ua.ros.taxiapp.web.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.ros.taxiapp.domain.Customer;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.web.controller.mobile.StatusMessage;

@Controller
@RequestMapping("/")
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

    @RequestMapping(value = "/perform_login", method = RequestMethod.POST)
    public void doLogin(@RequestParam("j_password") String j_password,
                        @RequestParam("j_username") String j_username) {

    }

    @RequestMapping(value = "/web/user/1", method = RequestMethod.GET)
    public String sayHello() {
        return "welcome";
    }


}
