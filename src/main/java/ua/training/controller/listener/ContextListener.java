package ua.training.controller.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;


/**
 * ContextListener put user loggedUsers to servlet context.
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

       final ServletContext servletContext = servletContextEvent.getServletContext();

       servletContext.setAttribute("loggedUsers", new HashSet<String>());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        final ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.removeAttribute("loggedUsers");
    }
}
