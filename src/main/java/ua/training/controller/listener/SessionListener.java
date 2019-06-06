package ua.training.controller.listener;


import ua.training.model.entity.User;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

import static ua.training.controller.command.TextConstants.Parameters.*;


public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {}

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession()
                .getServletContext()
                .getAttribute(LOGGED_USERS);

        String email = (String) httpSessionEvent.getSession()
                .getAttribute(EMAIL);

        loggedUsers.remove(email);

        httpSessionEvent.getSession()
                .setAttribute(LOGGED_USERS, loggedUsers);

        httpSessionEvent.getSession()
                .setAttribute(ROLE, User.ROLE.UNKNOWN);
    }
}
