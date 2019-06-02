<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<div class="col-md-3">
    <div class="profile-sidebar position-fixed">

        <!-- SIDEBAR MENU -->
        <div class="profile-usermenu sidebar-sticky">
            <h5 class="text-primary">
                <fmt:message key="label.client"/>
            </h5>
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/app/make-report" class="nav-link">
                        <i class="fa fa-home"></i>
                        <fmt:message key="submit.report"/>
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/edit-report">
                        <i class="fa fa-check"></i>
                        <fmt:message key="edit.report"/>
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/show-reports">
                        <i class="fa fa-user"></i>
                        <fmt:message key="see.all.reports"/>
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/make-complaint">
                        <i class="fa fa-check"></i>
                        <fmt:message key="make.complaint"/>
                    </a>
                </li>


            </ul>
        </div>
        <!-- END MENU -->
    </div>
</div>
















<!-- SIDEBAR USERPIC -->
<%--<div class="profile-userpic">--%>
<%--<img src="../../image/person.png" class="img-responsive portrait" alt="portrait">--%>
<%--</div>--%>
<%--<!-- END SIDEBAR USERPIC -->--%>
<%--<!-- SIDEBAR USER TITLE -->--%>
<%--<div class="profile-usertitle">--%>
<%--<div class="profile-usertitle-name">--%>
<%--{{param.name}}--%>
<%--</div>--%>
<%--<div class="profile-usertitle-job">--%>
<%--{{param.role}}--%>
<%--</div>--%>
<%--</div>--%>
<!-- END SIDEBAR USER TITLE -->