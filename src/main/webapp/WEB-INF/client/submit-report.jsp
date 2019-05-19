<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 17/05/19
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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

            <br>

            <div class="panel-heading clearfix">
                <i class="icon-calendar"></i>
                <h3 class="panel-title">Submit report</h3>
            </div>

            <form method="POST" class="" action="${pageContext.request.contextPath}/app/submit-report">

                <div class="panel-body">
                    <div class="row">

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
            <a class="" href="${pageContext.request.contextPath}/app/home">go home</a>
        </div>
    </div>
</div>

<!-- Row end -->
</body>
</html>


<%--<div class="col-md-3 col-sm-3">--%>
<%--<div class="input-group">--%>
<%--<span class="input-group-addon">--%>
<%--<input type="checkbox" checked="">--%>
<%--</span>--%>
<%--<span type="text" class="form-control">Дача</span>--%>
<%--</div>--%>
<%--</div>--%>