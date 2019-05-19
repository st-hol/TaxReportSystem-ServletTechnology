<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Index</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signin.css">
    <jsp:include page="${pageContext.request.contextPath}/css/bootstrap_min.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/js/jquery.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/js/bootstrap_min.jsp"/>

</head>

<body>
<div class="form">

    <c:if test="${param.dataInvalid == true}">
        <p style="color: orange"><fmt:message key="label.invalid.input"/></p>
    </c:if>

    <c:if test="${param.userExist == false}">
        <p style="color: darkred"><fmt:message key="label.not.exist"/></p>
    </c:if>

    <form class="form-horizontal" role="form" method="POST" action="${pageContext.request.contextPath}/app/login">
        <div class="form-group">
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">Логин</label>
                <div class="col-sm-10">
                    <input id="email" type="text" class="form-control" placeholder="Логин" name="email">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">Пароль</label>
                <div class="col-sm-10">
                    <input id="password" type="password" class="form-control" placeholder="Пароль" name="password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary btn-sm">Войти</button>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a class="" href="${pageContext.request.contextPath}/app/home">go home</a>
            </div>
        </div>
    </form>

</div>


</body>
</html>