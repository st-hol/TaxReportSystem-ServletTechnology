<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>
        <fmt:message key="edit.report"/>
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
    <div class="container col-lg-3 offset-5">

        <div class="panel-heading clearfix">
            <h3 class="panel-title">
                <fmt:message key="edit.report"/>
            </h3>
        </div>

        <form method="POST" class="" action="${pageContext.request.contextPath}/app/submit-edit-report">

            <div class="form-group">
                <div class="">
                    <select class="soflow-color" name="idReportToChange" required>
                        <option value=""><fmt:message key="placeholder.choose.report"/></option>
                        <c:forEach var="report" items="${reportsToChange}">
                            <option value="${report.id}">
                                id: <c:out value="${report.id}"/> |
                                company: <c:out value="${report.companyName}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <div>
                    <input maxlength="45" minlength="1" name="companyName" type="text"
                           placeholder="<fmt:message key="placeholder.company.name"/>" required>
                </div>
            </div>

            <div class="form-group">
                <div class="">
                    <input maxlength="45" minlength="1" name="taxpayerCode" type="number"
                           placeholder="<fmt:message key="placeholder.taxpayer.code"/>" required>
                </div>
            </div>

            <br>

            <div class="form-group">
                <div class="">
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