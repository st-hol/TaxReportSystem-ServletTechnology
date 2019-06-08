<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>
        <fmt:message key="see.all.reports"/>
    </title>
    <meta name="viewport" content="width=device-width"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/report-list.css"/>
</head>
<body>

<div class="table-cont">
    <table class="responstable" border="1" cellpadding="3" cellspacing="3">
        <tr>
            <th><fmt:message key="placeholder.company.name"/></th>
            <th><fmt:message key="placeholder.taxpayer.code"/></th>
            <th><fmt:message key="completion.time"/></th>
            <th><fmt:message key="total.amount"/></th>
            <th><fmt:message key="is.accepted"/></th>
            <th><fmt:message key="should.change"/></th>
            <th><fmt:message key="insp.comment"/></th>
        </tr>

        <c:forEach var="report" items="${reports}">
            <tr>
                <td><c:out value="${report.companyName}"/></td>
                <td><c:out value="${report.taxpayerCode}"/></td>
                <td><c:out value="${report.completionTime}"/></td>
                <td><c:out value="${report.totalAmountOfProperty}"/></td>
                <td><c:out value="${report.isAccepted}"/></td>
                <td><c:out value="${report.shouldBeChanged}"/></td>
                <td><c:out value="${report.inspectorComment}"/></td>
            </tr>
        </c:forEach>
    </table>


    <nav>
        <ul class="pagination">

            <c:if test="${currentPage != 1}">
                <li class="page-item">
                    <a class="in-table-link"
                       href="${pageContext.request.contextPath}/app/show-reports?currentPage=${currentPage - 1}">
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
                               href="${pageContext.request.contextPath}/app/show-reports?currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>


            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="in-table-link"
                                         href="${pageContext.request.contextPath}/app/show-reports?currentPage=${currentPage + 1}">
                    <fmt:message key="label.next"/>
                </a>
                </li>
            </c:if>
        </ul>
    </nav>

    <br>
    <div class="home">
        <a class="" href="${pageContext.request.contextPath}/app/personal-cabinet">
            <fmt:message key="back.to.cabinet"/>
        </a>
    </div>
</div>


</body>
</html>
