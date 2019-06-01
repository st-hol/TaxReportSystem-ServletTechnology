package ua.training.controller.filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;


//public class CookieLocaleFilter implements Filter {
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        if (req.getParameter("cookieLocale") != null) {
//            Cookie cookie = new Cookie("lang", req.getParameter("cookieLocale"));
//            res.addCookie(cookie);
//        }
//
//        chain.doFilter(request, response);
//    }
//
//    public void destroy() {}
//    public void init(FilterConfig arg0) throws ServletException {}
//
//}


//
//
//
//
//
////
////@WebFilter(filterName = "CookieLocaleFilter", urlPatterns = { "/*" })
////public class CookieLocaleFilter implements Filter {
////
////    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
////            throws IOException, ServletException {
////        HttpServletRequest req = (HttpServletRequest) request;
////        HttpServletResponse res = (HttpServletResponse) response;
////
////        if (req.getParameter("cookieLocale") != null) {
////            Cookie cookie = new Cookie("lang", req.getParameter("cookieLocale"));
////            res.addCookie(cookie);
////        }
//////        else {
//////            Cookie cookie = new Cookie("lang", Locale.getDefault().getLanguage());
//////            res.addCookie(cookie);
//////        }
////        chain.doFilter(request, response);
////    }
////
////    public void destroy() {}
////    public void init(FilterConfig arg0) throws ServletException {
////        Locale.setDefault(Locale.ENGLISH);
////    }
////
////}
//
//
//
//
//
//
//
//
//
//
////import java.util.Locale;
////@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
////public class SessionLocaleFilter implements Filter {
////    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
////            throws IOException, ServletException {
////
////        HttpServletRequest req = (HttpServletRequest) request;
////
////        if (req.getParameter("sessionLocale") != null) {
////            req.getSession().setAttribute("lang", req.getParameter("sessionLocale"));
////        } else {
////            req.getSession().setAttribute("lang", Locale.getDefault().getLanguage());
////        }
////        chain.doFilter(request, response);
////    }
////    public void destroy() {}
////    public void init(FilterConfig arg0) throws ServletException {
////        Locale.setDefault(Locale.ENGLISH);
////    }
////}