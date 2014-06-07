package ua.ros.taxiapp.web.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import ua.ros.taxiapp.services.TaxistService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ros on 07.06.14.
 */
public class MyLogoutHandler extends
        SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private TaxistService taxistService;

//    @Override
    public void onLogoutSuccess
            (HttpSession session, Authentication authentication)
            throws IOException, ServletException {
//        String refererUrl = request.getHeader("Referer");
//        auditService.track("Logout from: " + refererUrl);
//
//        super.onLogoutSuccess(request, response, authentication);
    }
}