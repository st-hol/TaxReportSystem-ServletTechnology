<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">

<head>
    <title>
        <fmt:message key="tax.report"/>
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
    <jsp:include page="${pageContext.request.contextPath}/css/bootstrap_min.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/js/jquery.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/js/bootstrap_min.jsp"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax-submit-report.js"></script>

</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/common/navbar-landing.jsp"/>

<header class="header">
    <div class="overlay"></div>
    <div class="container offset-1">
        <div class="description text-left">
            <h3>
                <span>
                    <fmt:message key="tax.report"/>
                </span>
                <p>
                    <fmt:message key="landing.welcome"/>
                </p>
            </h3>
        </div>

    </div>
</header>


<script src="${pageContext.request.contextPath}/js/ajax-submit-report.js"></script>
</body>

</html>


