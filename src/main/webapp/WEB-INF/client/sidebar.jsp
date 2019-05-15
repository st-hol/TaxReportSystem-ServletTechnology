<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 15/04/19
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>


<aside class="sidebar">
    <nav class="nav">
        <ul>
            <li class="active"><a href="#">[<fmt:message key="label.student"/>]:</a></li>
            <li><a href="${pageContext.request.contextPath}/introductory-campaign/reg-exam"><fmt:message key="label.reg.for.exams"/></a></li>
            <li><a href="${pageContext.request.contextPath}/introductory-campaign/apply-admission"><fmt:message key="label.apply.adm"/></a></li>
            <li><a href="${pageContext.request.contextPath}/introductory-campaign/list-of-enrolled"><fmt:message key="label.enrolled.list"/></a></li>
            <li><a href="${pageContext.request.contextPath}/introductory-campaign/home"><fmt:message key="label.to.main"/></a></li>
            <li><a href="${pageContext.request.contextPath}/introductory-campaign/logout"><fmt:message key="label.logout"/></a></li>
        </ul>
    </nav>
</aside>


