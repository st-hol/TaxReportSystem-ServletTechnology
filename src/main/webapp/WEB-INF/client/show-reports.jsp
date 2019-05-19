<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>


<html>
<head>
    <title><fmt:message key="label.reports.list"/></title>


    <meta name="viewport" content="width=device-width"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <%--<jsp:include page="${pageContext.request.contextPath}/css/bootstrap_min.jsp"/>--%>
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/personal-cabinet.css"/>--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/report-list.css"/>

</head>
<body>

<div class="table-cont">
    <table class="responstable" border="1" cellpadding="3" cellspacing="3">
        <tr>
            <th><fmt:message key="label.company.name"/></th>
            <th><fmt:message key="label.taxpayer.code"/></th>
            <th><fmt:message key="label.completion.time"/></th>
            <th><fmt:message key="label.total.amount"/></th>
            <th><fmt:message key="label.is.accepted"/></th>
            <th><fmt:message key="label.should.change"/></th>
            <th><fmt:message key="label.insp.comment"/></th>
        </tr>

        <c:forEach var="report" items="${reports}">
            <tr>
                <td>${report.companyName}</td>
                <td>${report.taxpayerCode}</td>
                <td>${report.completionTime}</td>
                <td>${report.totalAmountOfProperty}</td>
                <td>${report.isAccepted}</td>
                <td>${report.shouldBeChanged}</td>
                <td>${report.inspectorComment}</td>
            </tr>
        </c:forEach>
    </table>


    <nav>
        <ul class="pagination">

            <c:if test="${currentPage != 1}">
                <li class="page-item">
                    <a class="in-table-link"
                       href="${pageContext.request.contextPath}/app/show-reports?page=${currentPage - 1}">
                        <fmt:message key="label.prev"/>
                    </a>
                </li>
            </c:if>


            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active">
                            <a class="in-table-link"
                               href="#"> ${i} </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="in-table-link"
                               href="${pageContext.request.contextPath}/app/show-reports?page=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>


            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="in-table-link"
                                         href="${pageContext.request.contextPath}/app/show-reports?page=${currentPage + 1}">
                    <fmt:message key="label.next"/>
                </a>
                </li>
            </c:if>
        </ul>
    </nav>

    <br>
    <div class="home">
        <a class="" href="${pageContext.request.contextPath}/app/home">go home</a>
    </div>
</div>


</body>
</html>
