<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 17/05/19
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>subm</title>

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
            <div class="panel-heading clearfix">
                <i class="icon-calendar"></i>
                <h3 class="panel-title">Make a complaint</h3>
            </div>

            <form method="POST" class="" action="${pageContext.request.contextPath}/app/submit-complaint">
                <div class="panel-body">
                    <div class="row">

                        <br>

                        <div class="col-md-3 col-sm-3">
                            <div class="input-group">
                             <span class="input-group-addon">
                                 <textarea maxlength="45" minlength="5" name="content" placeholder="write your charge here" required></textarea>
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



