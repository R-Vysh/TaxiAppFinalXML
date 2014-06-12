package ua.ros.taxiapp.web.controller.web;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ua.ros.taxiapp.domain.Taxist;
import ua.ros.taxiapp.services.TaxistService;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Component
public class MyHttpSessionListener implements HttpSessionListener, ApplicationContextAware {

    @Autowired
    TaxistService taxistService;

    // Public constructor is required by servlet spec
    public MyHttpSessionListener() {
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    @Override
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
        if(se.getSession().getAttribute("taxist") != null) {
            taxistService.setOffline((Taxist) se.getSession().getAttribute("taxist"));
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext instanceof WebApplicationContext) {
            ((WebApplicationContext) applicationContext).getServletContext().addListener(this);
        } else {
            //Either throw an exception or fail gracefully, up to you
            throw new RuntimeException("Must be inside a web application context");
        }
    }
}
