<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>


<nav class="navbar navbar-expand-lg fixed-top bg-dark ">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/app/home">
        <fmt:message key="go.home"/>
    </a>

    <a class="navbar-brand en-icon" href="?sessionLocale=en">
        <img class="lang-icon" src="${pageContext.request.contextPath}/image/eng.png"
             alt="<fmt:message key="lang.en"/>">
    </a>

    <a class="navbar-brand ua-icon" href="?sessionLocale=ua">
        <img class="lang-icon" src="${pageContext.request.contextPath}/image/ukr.png"
             alt="<fmt:message key="lang.ua"/>">
    </a>


    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse " id="navbarSupportedContent">
        <ul class="navbar-nav mr-4">

            <%--<li class="nav-item en-icon">--%>
            <%--<a class="nav-link" href="?sessionLocale=en">--%>
            <%--<img class="lang-icon" src="${pageContext.request.contextPath}/image/eng.png"--%>
            <%--alt="<fmt:message key="lang.en"/>">--%>
            <%--&lt;%&ndash;<span class="flag-icon flag-icon-us"></span><fmt:message key="lang.en"/>&ndash;%&gt;--%>
            <%--</a>--%>
            <%--</li>--%>

            <%--<li class="nav-item ua-icon">--%>
            <%--<a class="nav-link" href="?sessionLocale=ua">--%>
            <%--<img class="lang-icon" src="${pageContext.request.contextPath}/image/ukr.png"--%>
            <%--alt="<fmt:message key="lang.ua"/>">--%>
            <%--&lt;%&ndash;<span class="flag-icon flag-icon-mx"></span><fmt:message key="lang.ua"/>&ndash;%&gt;--%>
            <%--</a>--%>
            <%--</li>--%>


            <li class="nav-item">
                <a class="nav-link " href="${pageContext.request.contextPath}/app/personal-cabinet">
                    <fmt:message key="pers.cab"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/log-me">
                    <fmt:message key="sign.in"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="${pageContext.request.contextPath}/app/reg-me">
                    <fmt:message key="sign.up"/>
                </a>
            </li>


        </ul>

    </div>
</nav>

