<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="sign.up"/></title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <jsp:include page="${pageContext.request.contextPath}/css/bootstrap_min.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/js/jquery.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/js/bootstrap_min.jsp"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax-submit-report.js"></script>


</head>

<body>
<section>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-8 col-lg-8 col-xl-6">
                <div class="row">
                    <div class="col text-center">
                        <h1>
                            <fmt:message key="sign.up"/>
                        </h1>
                    </div>
                </div>

                <c:if test="${param.passwordsDifferent == true}">
                    <p style="color: orange"><fmt:message key="passwords.diff"/></p>
                </c:if>
                <c:if test="${param.dataInvalid == true}">
                    <p style="color: orange"><fmt:message key="invalid.input"/></p>
                </c:if>
                <c:if test="${param.success == true}">
                    <p style="color: green"><fmt:message key="reg.success"/></p>
                </c:if>
                <c:if test="${param.alreadyExist == true}">
                    <p style="color: darkred"><fmt:message key="user.exist"/></p>
                </c:if>

                <form method="post" action="${pageContext.request.contextPath}/app/registration">

                    <div class="row align-items-center">
                        <div class="col mt-4">
                            <input name="firstName" type="text" class="form-control"
                                   placeholder="<fmt:message key="placeholder.name"/>">
                        </div>
                    </div>
                    <div class="row align-items-center">
                        <div class="col mt-4">
                            <input name="lastName" type="text" class="form-control"
                                   placeholder="<fmt:message key="placeholder.surname"/>">
                        </div>
                    </div>
                    <div class="row align-items-center mt-4">
                        <div class="col">
                            <input name="email" type="email" class="form-control"
                                   placeholder="<fmt:message key="placeholder.email"/>">
                        </div>
                    </div>
                    <div class="row align-items-center mt-4">
                        <div class="col">
                            <input name="password" type="password" class="form-control"
                                   placeholder="<fmt:message key="placeholder.password"/>">
                        </div>
                        <div class="col">
                            <input name="confirmPassword" type="password" class="form-control"
                                   placeholder="<fmt:message key="placeholder.confirm.password"/>">
                        </div>
                    </div>

                    <div class="row align-items-center mt-4">
                        <select class="soflow-color" name="role" required>
                            <option value=""><fmt:message key="placeholder.choose.role" /></option>
                            <option value="INSPECTOR"><fmt:message key="label.inspector" /></option>
                            <option value="CLIENT"><fmt:message key="label.client" /></option>
                        </select>
                    </div>

                    <div class="row justify-content-start mt-4">
                        <div class="col">
                            <div class="form-check">
                                <label class="form-check-label">
                                    <input type="checkbox" class="form-check-input">
                                    <fmt:message key="label.accept.terms"/>
                                </label>
                            </div>

                            <button class="btn btn-primary mt-4">
                                <fmt:message key="label.submit"/>
                            </button>
                        </div>
                    </div>
                </form>


                <a class="" href="${pageContext.request.contextPath}/app/home">
                    <fmt:message key="back.to.cabinet"/>
                </a>

            </div>
        </div>
    </div>
</section>
</body>
</html>


