<div class="col-md-3">
    <div class="profile-sidebar position-fixed">
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

        <!-- SIDEBAR MENU -->
        <div class="profile-usermenu sidebar-sticky">
            <h5 class="text-primary">CLIENT:</h5>
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/app/make-report" class="nav-link">
                        <i class="fa fa-home"></i>
                        Submit a report </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/edit-report">
                        <i class="fa fa-check"></i>
                        Edit report </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/show-reports">
                        <i class="fa fa-user"></i>
                        Show my reports </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/make-complaint">
                        <i class="fa fa-check"></i>
                        Make a complaint </a>
                </li>


            </ul>
        </div>
        <!-- END MENU -->
    </div>
</div>