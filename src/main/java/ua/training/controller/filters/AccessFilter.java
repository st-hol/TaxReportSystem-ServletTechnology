package ua.training.controller.filters;

import ua.training.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;
import static ua.training.controller.command.TextConstants.Parameters.*;
import static ua.training.controller.command.TextConstants.Routes.*;


public class AccessFilter implements Filter {
    private Map<User.ROLE, Set<String>> allowedRoutes;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedRoutes = new HashMap<>();
        allowedRoutes.put(User.ROLE.UNKNOWN,
                Stream.of("/", "/reg-me", "/log-me", "/login", "/registration", "/home")
                        .collect(collectingAndThen(
                                toCollection(HashSet::new), Collections::unmodifiableSet)));



        allowedRoutes.put(User.ROLE.CLIENT,
                Stream.of("/", "/logout", "/home", "/login", "/registration", "/show-reports", "/personal-cabinet",
                        "/make-report","/edit-report", "/make-complaint",
                        "/submit-report", "/submit-complaint", "/submit-edit-report")
                        .collect(collectingAndThen(
                                toCollection(HashSet::new), Collections::unmodifiableSet)));


        allowedRoutes.put(User.ROLE.INSPECTOR,
                Stream.of("/", "/logout", "/home", "/login", "/registration", "/personal-cabinet",
                        "/check-report", "/set-taxable",
                        "/submit-checking-report", "/submit-set-taxable")
                        .collect(collectingAndThen(
                                toCollection(HashSet::new), Collections::unmodifiableSet)));
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI()
                .replace(request.getContextPath(), EMPTY_STRING)
                .replace(request.getServletPath(), EMPTY_STRING);

        if (request.getSession().getAttribute(ROLE) == null) {
            request.getSession().setAttribute(ROLE, User.ROLE.UNKNOWN);
        }
        User.ROLE currentRole = ((User.ROLE)request.getSession().getAttribute(ROLE));


        if ( allowedRoutes.get(currentRole).contains(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //user is guest? then sign in
            if(currentRole.equals(User.ROLE.UNKNOWN)){
                request.getRequestDispatcher(TO_LOGIN).forward(request,response);
            }
            request.getRequestDispatcher(ACCESS_FORBIDDEN_403).forward(request,response);
        }
    }

    @Override
    public void destroy() {}
}
