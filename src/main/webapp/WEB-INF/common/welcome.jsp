<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>

<html lang="${cookie['lang'].value}">

<head>
    <title>Index</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
    <jsp:include page="${pageContext.request.contextPath}/css/bootstrap_min.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/js/jquery.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/js/bootstrap_min.jsp"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>

</head>
<body>


<jsp:include page="navbar-landing.jsp"/>


<header class="header">
    <div class="overlay"></div>
    <div class="container offset-1">
        <div class="description text-left">
            <h3>
                <span> ▍ Tax report system</span>
                <p>
                    Hello guest! You can sign in if you already have account or sign up if you are new here.</p>
                <%--<button class="btn btn-outline-secondary">See more</button>--%>
            </h3>
        </div>

    </div>
</header>


</body>

</html>


