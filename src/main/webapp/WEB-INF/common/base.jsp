<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>

    <c:choose>
        <c:when test="${sessionScope.role == 'INSPECTOR'}">
            <title><fmt:message key="label.inspector"/></title>
        </c:when>
        <c:when test="${sessionScope.role == 'CLIENT'}">
            <title><fmt:message key="label.client"/></title>
        </c:when>
        <c:otherwise>
            <jsp:forward page="app/home"/>
        </c:otherwise>
    </c:choose>


    <meta name="viewport" content="width=device-width"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <jsp:include page="${pageContext.request.contextPath}/css/bootstrap_min.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/js/jquery.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/js/bootstrap_min.jsp"/>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbar.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/personal-cabinet.css"/>
</head>
<body>

<!--navbar-->
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/common/navbar-cabinet.jsp"/>

<!--our content goes here-->
<div class="container content">
    <div class="row profile">

        <c:choose>
            <c:when test="${sessionScope.role == 'INSPECTOR'}">
                <jsp:include page="${pageContext.request.contextPath}/WEB-INF/inspector/sidebar.jsp"/>
                <jsp:include page="${pageContext.request.contextPath}/WEB-INF/inspector/default-content.jsp"/>
            </c:when>
            <c:when test="${sessionScope.role == 'CLIENT'}">
                <jsp:include page="${pageContext.request.contextPath}/WEB-INF/client/sidebar.jsp"/>
                <jsp:include page="${pageContext.request.contextPath}/WEB-INF/client/default-content.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:forward page="app/home"/>
            </c:otherwise>
        </c:choose>

    </div>
</div>





<div class="container">
    <div class="row">
        <div class="col-md-3">
        </div>
        <div class="col-md-9 ft">
            <footer class="footer">
                <div class="row">
                    <div class="col-md-4">
                        <span class="copyright">Copyright &copy; TaxReportSystem 2019</span>
                    </div>
                </div>
            </footer>
        </div>
    </div>
</div>
</body>
</html>



