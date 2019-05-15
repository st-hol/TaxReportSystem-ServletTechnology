<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 13/04/19
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>


<ul class="topnav" id="myTopnav">
    <li id="log-li"><a href="${pageContext.request.contextPath}/introductory-campaign/home#top" class="activeMenuItem" id="log"><img
            src="${pageContext.request.contextPath}/image/book35x35_rev.png"></a></li>

    <li><a href="${pageContext.request.contextPath}/introductory-campaign/home#about"><fmt:message key="label.studying.programs" /></a></li>
    <li><a href="${pageContext.request.contextPath}/introductory-campaign/home#best"><fmt:message key="label.to.do" /></a></li>
    <li><a href="${pageContext.request.contextPath}/introductory-campaign/home#team"><fmt:message key="label.service.roles" /></a></li>

    <li class="contact-us">
        <a href="${pageContext.request.contextPath}/introductory-campaign/personal-cabinet">
            <fmt:message key="label.student" />
            <fmt:message key="label.cabinet" />
        </a>
    </li>


    <li class="my-nav-soc-li">
        <a href="?cookieLocale=en"><img class="soc-icon" src="${pageContext.request.contextPath}/image/locale/eng.png"></a>
        <a href="?cookieLocale=ua"><img class="soc-icon" src="${pageContext.request.contextPath}/image/locale/ukr.png"></a>
    </li>

    <li class="icon"><a href="javascript:void(0);" style="font-size:15px; margin: 0px; padding: 0px; padding-top: 8px;"
                        onclick="myFunction()">
        <img style="width: 37px; color: white;" src="${pageContext.request.contextPath}/image/share/menu-white.png">
        <!-- &#9776; --></a></li>
</ul>

