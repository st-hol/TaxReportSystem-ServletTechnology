<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>


<div class="col-md-9">
    <div class="profile-content">
        <fmt:message key="task.variant"/>
        <%--<fmt:message key="inspector.can"/>--%>
        <%--<ul class="list-group">--%>
            <%--<li class="list-group-item">--%>
                <%--<fmt:message key="check.reports"/>--%>
            <%--</li>--%>
            <%--<li class="list-group-item">--%>
                <%--<fmt:message key="reg.prop"/>--%>
            <%--</li>--%>
        <%--</ul>--%>
    </div>
</div>
