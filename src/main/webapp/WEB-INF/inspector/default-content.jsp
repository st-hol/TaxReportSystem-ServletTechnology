<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>

<div class="welcome-title-div">
    <span class="welcome-title"><fmt:message key="label.hello"/> ${sessionScope.email}!</span>
</div>





<style>
    .welcome-title-div{
        margin-top: 100px;
        text-align: center;
    }
    .welcome-title{
        font-size: 20px;
        padding: 30px 0;
        margin: 0 30%;
    }

</style>

