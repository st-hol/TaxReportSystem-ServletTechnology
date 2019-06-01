<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>edit</title>

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
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="panel panel-default">

            <br>

            <div class="panel-heading clearfix">
                <i class="icon-calendar"></i>
                <h3 class="panel-title">edit report</h3>
            </div>

            <form method="POST" class="" action="${pageContext.request.contextPath}/app/submit-edit-report">

                <div class="panel-body">
                    <div class="row">


                        <div class="col-md-3 col-sm-3">
                            <div class="input-group">
                             <span class="input-group-addon">
                                 <select class="soflow-color" name="idReportToChange" required>
                                     <option value=""><fmt:message key="placeholder.choose.report"/></option>
                                     <c:forEach var="report" items="${reportsToChange}">
                                         <option value="${report.id}">id: ${report.id} | company: ${report.companyName}
                                         </option>
                                     </c:forEach>
                                </select>
                             </span>
                            </div>
                        </div>

                        <div class="col-md-3 col-sm-3">
                            <div class="input-group">
                             <span class="input-group-addon">
                                <input maxlength="45" minlength="1" name="companyName" type="text"
                                       placeholder="company name" required>
                             </span>
                            </div>
                        </div>

                        <br>

                        <div class="col-md-3 col-sm-3">
                            <div class="input-group">
                             <span class="input-group-addon">
                                <input maxlength="45" minlength="1" name="taxpayerCode" type="number"
                                       placeholder="taxpayer code" required>
                             </span>
                            </div>
                        </div>

                        <br>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-primary btn-sm">submit</button>
                            </div>
                        </div>

                    </div>
                </div>
            </form>
            <a class="" href="${pageContext.request.contextPath}/app/personal-cabinet">back to cabinet</a>
        </div>
    </div>
</div>

<!-- Row end -->
</body>
</html>