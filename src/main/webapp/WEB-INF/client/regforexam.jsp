<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Reg. for exam</title>

    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/image/book22px.png">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/libs/slick/slick.css"/>
    <%--<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>--%>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/libs/slick/slick-theme.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/libs/animate.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sidebar.css"/>


    <meta name="keywords" content="">
    <meta name="description" content="Introductory campaign">
    <meta name="viewport" content="width=device-width"/>
</head>
<body>

<jsp:include page="navbar.jsp"/>
<jsp:include page="sidebar.jsp"/>



<div class="form-cont">
<div class="form-cont-items">
    <div class="form-item put-marks-item" id="popupform">
        <h2 class="form-title wow bounce"><fmt:message key="label.registrate.for.subject"/> </h2>
        <div class="contee wow flipInY" data-wow-delay="0.5s">


            <c:if test="${param.alreadyExist == true}">
                <p style="color: orange"><fmt:message key="label.already.exist" /></p>
            </c:if>
            <form class="put-marks-form" method="post" action="${pageContext.request.contextPath}/introductory-campaign/registrate-for-exam">
                <%--<input type="text" name="examId"><br/>--%>

                <select class="soflow-color" name="examId" required>
                    <option value=""><fmt:message key="label.choose.exam"/></option>
                    <c:forEach var="exam" items="${exams}">
                        <option value="${exam.id}">${exam.examName}</option>
                    </c:forEach>
                </select>


                <input class="button" type="submit" value="<fmt:message key="label.reg.for.exams"/>">
            </form>
        </div>
    </div>
</div>
</div>





<style>
    .form-cont-items {
        width: 40%;
    }

    .put-marks-item{
        margin-top: 100px;
        width: auto;
    }

    .put-marks-form{
        max-width: 100%;
    }
    .put-marks-form input{
        width: auto;
        font-size: 14px;
        margin: 50px auto;
        outline: none;
        border-radius: 25px;
    }
    .put-marks-item input[type="submit"]{
        margin: 0 auto;
    }


    select.soflow-color {
        outline: none;
        -webkit-appearance: button;
        -webkit-border-radius: 2px;
        -webkit-box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.1);
        -webkit-padding-end: 20px;
        -webkit-padding-start: 2px;
        -webkit-user-select: none;
        background-image: url(http://i62.tinypic.com/15xvbd5.png), -webkit-linear-gradient(#FAFAFA, #F4F4F4 40%, #E5E5E5);
        background-position: 97% center;
        background-repeat: no-repeat;
        border: 1px solid #AAA;
        color: #555;
        font-size: inherit;
        margin: 20px;
        overflow: hidden;
        padding: 5px 10px;
        text-overflow: ellipsis;
        white-space: nowrap;
        width: 300px;
    }

    select.soflow-color {

        color: #fff;
        background-image: url(http://i62.tinypic.com/15xvbd5.png), -webkit-linear-gradient(orange, orange 30%, orange);
        background-color: orange;
        -webkit-border-radius: 20px;
        -moz-border-radius: 20px;
        border-radius: 20px;
        padding-left: 15px;
    }
</style>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/myscript.js"></script>


</body>
</html>