<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>


<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ page isELIgnored="false" %>--%>

<%--<fmt:setLocale value="${cookie['lang'].value}"/>--%>
<%--<fmt:setBundle basename="messages"/>--%>


<%--<html>--%>
<%--<head>--%>
    <%--<title><fmt:message key="label.client"/></title>--%>

    <%--<meta name="viewport" content="width=device-width"/>--%>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>

    <%--<jsp:include page="${pageContext.request.contextPath}/css/bootstrap_min.jsp"/>--%>
    <%--<jsp:include page="${pageContext.request.contextPath}/js/jquery.jsp"/>--%>
    <%--<jsp:include page="${pageContext.request.contextPath}/js/bootstrap_min.jsp"/>--%>

    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/personal-cabinet.css"/>--%>
<%--</head>--%>
<%--<body>--%>

<%--<!--navbar-->--%>
<%--<jsp:include page="../client/navbar-cabinet.jsp"/>--%>

<%--<!--our content goes here-->--%>
<%--<div class="container content">--%>
    <%--<div class="row profile">--%>

        <%--<jsp:include page="../client/sidebar.jsp"/>--%>

        <%--<jsp:include page="../client/default-content.jsp"/>--%>
    <%--</div>--%>
<%--</div>--%>


<%--&lt;%&ndash;<c:choose>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<c:when test="${sessionScope.role == 'ADMIN'}">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<jsp:include page="../WEB-INF/admin/navbar-cabinet.jsp"/>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</c:when>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<c:when test="${sessionScope.role == 'USER'}">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<jsp:include page="../WEB-INF/user/navbar-cabinet.jsp"/>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</c:when>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<c:otherwise>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<jsp:include page="navbar-cabinet.jsp"/>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</c:otherwise>&ndash;%&gt;--%>
<%--&lt;%&ndash;</c:choose>&ndash;%&gt;--%>


<%--<div class="container">--%>
    <%--<div class="row">--%>
        <%--<div class="col-md-3">--%>
        <%--</div>--%>
        <%--<div class="col-md-9 ft">--%>
            <%--<footer class="footer">--%>
                <%--<div class="row">--%>
                    <%--<div class="col-md-4">--%>
                        <%--<span class="copyright">Copyright &copy; TaxReportSystem 2019</span>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</footer>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>



