package ua.ros.taxiapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SiteController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String printHello() {
        return "Welcome. Authorization success";
    }
}
