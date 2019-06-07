<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="${pageContext.request.contextPath}/app/home">
            <fmt:message key="tax.report"/>
        </a>

        <div>
            <ul class="navbar-nav text-uppercase ml-auto">
                <li class="nav-item en-icon" style="margin-left: 0 !important;">
                    <a class="nav-link" href="?sessionLocale=en">
                        <img class="lang-icon" src="${pageContext.request.contextPath}/image/eng.png"
                             alt="<fmt:message key="lang.en"/>">
                        <%--<span class="flag-icon flag-icon-us"></span><fmt:message key="lang.en"/>--%>
                    </a>
                </li>

                <li class="nav-item ua-icon">
                    <a class="nav-link" href="?sessionLocale=ua">
                        <img class="lang-icon" src="${pageContext.request.contextPath}/image/ukr.png"
                             alt="<fmt:message key="lang.ua"/>">
                        <%--<span class="flag-icon flag-icon-mx"></span><fmt:message key="lang.ua"/>--%>
                    </a>
                </li>


                <li class="nav-item logout-btn">
                    <a class="btn btn-primary my-2 my-sm-0" href="${pageContext.request.contextPath}/app/logout" type="submit">
                        <i class="fa fa-sign-out"></i>
                        <fmt:message key="log.out"/>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>