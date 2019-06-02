<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 17/05/19
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>
        <fmt:message key="make.complaint"/>
    </title>

    <meta name="viewport" content="width=device-width"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <jsp:include page="${pageContext.request.contextPath}/css/bootstrap_min.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/js/jquery.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/js/bootstrap_min.jsp"/>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/personal-cabinet.css"/>
</head>
<body>

<!-- Row start -->
<div class="row">
    <div class="container col-lg-3 offset-4">

        <div class="panel-heading clearfix">
            <i class="icon-calendar"></i>
            <h3 class="panel-title">
                <fmt:message key="make.complaint"/>
            </h3>
        </div>

        <form method="POST" class="" action="${pageContext.request.contextPath}/app/submit-complaint">
            <div class="form-group">
                <div class="">
                        <textarea maxlength="45" minlength="5" name="content"
                                  placeholder="<fmt:message key="placeholder.write.charge"/>"
                                  class="col-12" required>
                        </textarea>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary btn-sm">
                        <fmt:message key="label.submit"/>
                    </button>
                </div>
            </div>

        </form>

        <a class="" href="${pageContext.request.contextPath}/app/personal-cabinet">
            <fmt:message key="back.to.cabinet"/>
        </a>
    </div>
</div>
<!-- Row end -->
</body>
</html>



