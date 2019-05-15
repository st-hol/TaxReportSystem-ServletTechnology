package ua.training.controller.filters;//package org.training.controller.filters;
//
//
//import User;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.util.*;
//import java.util.stream.Stream;
//
//import static java.util.stream.Collectors.collectingAndThen;
//import static java.util.stream.Collectors.toCollection;
//
//
//public class PreventComebackFilter implements Filter {
//    private Set<String> securedRoutes;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        securedRoutes = new HashSet<>();
//        securedRoutes.addAll(
//                Stream.of("/registrate-for-exam", "/apply-for-admission", "/list-of-enrolled",
//                        "/apply-admission", "/reg-exam", "/show-all-exams", "/personal-cabinet",
//                        "/set-grade", "/put-marks")
//                        .collect(collectingAndThen(
//                                toCollection(HashSet::new), Collections::unmodifiableSet)));
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest,
//                         ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String path = request.getRequestURI()
//                .replace(request.getContextPath(), "")
//                .replace(request.getServletPath(), "");
//
//        final HttpSession session = request.getSession();
//
//        //final String path = request.getServletContext().getContextPath();
//
//
////        //to prevent user coming back to cached pages after logout
////        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
////        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
////        response.setHeader("Pragma","no-cache");
////        response.setDateHeader ("Expires", 0);
////        if (session.getAttribute("email") == null || session.getAttribute("password") == null
////                || session.getAttribute("role") == null) {
////            response.sendRedirect(path + "/jsp/error/invalidSession.jsp");
////            //return  "redirect@" + path + "/jsp/error/invalidSession.jsp";
////        }
//
//        boolean isNotPresentSessionUser = session.getAttribute("email") == null || session.getAttribute("password") == null
//                || session.getAttribute("role") == null;
//
//
//        if (securedRoutes.contains(path) && isNotPresentSessionUser) {
//
//            //to prevent user coming back to cached pages after logout
//            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
//            response.setHeader("Pragma", "no-cache");
//            response.setDateHeader("Expires", 0);
//
//
//            String redirectPath = request.getServletContext().getContextPath();
//            response.sendRedirect(redirectPath + "/jsp/error/invalidSession.jsp");
//            //request.getRequestDispatcher("/jsp/error/invalidSession.jsp").forward(request, response);
//
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
