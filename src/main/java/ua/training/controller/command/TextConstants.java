package ua.training.controller.command;

public interface TextConstants {
    //request parameters
    String EMAIL = "email";
    String PASSWORD = "password";
    String ROLE = "role";
    String CONFIRM_PASSWORD = "confirmPassword";
    String FIRST_NAME = "firstName";
    String LAST_NAME = "lastName";
    String LOGGED_USERS = "loggedUsers";
    String CURRENT_PAGE = "currentPage";
    String NO_OF_PAGES = "noOfPages";
    String REPORTS = "reports";
    String REPORTS_TO_CHANGE = "reportsToChange";
    String ITEMS = "items";
    String PERSONS = "persons";
    String COMPANY_NAME = "companyName";
    String TAXPAYER_CODE = "taxpayerCode";
    String CONTENT = "content";
    String ID_REPORT_TO_CHANGE = "idReportToChange";
    String ID_ITEM = "idItem";
    String ID_PERSON = "idPerson";
    String QUANTITY = "quantity";

    String ID_REPORT = "idReport";
    String IS_ACCEPTED = "isAccepted";
    String SHOULD_BE_CHANGED = "shouldBeChanged";
    String INSPECTOR_COMMENT = "inspectorComment";


    //routes
    String REDIRECT = "redirect@";
    String LOGIN_FAIL_INVALID_INPUT = "/WEB-INF/common/login.jsp?dataInvalid=true";
    String PERSONAL_CABINET = "/app/personal-cabinet";
    String MULTILOGIN_ERROR = "/WEB-INF/common/error/multilogin.jsp";
    String INVALID_SESSION_ERROR = "/WEB-INF/common/error/invalidSession.jsp";

    String BASE = "/WEB-INF/common/base.jsp";
    String USER_NOT_EXIST = "/WEB-INF/common/login.jsp?userExist=false";

    String REGISTRATION_FAIL_INVALID_DATA = "/WEB-INF/common/registration.jsp?dataInvalid=true";
    String REGISTRATION_FAIL_PASSWORDS_DIFFERENT =  "/WEB-INF/common/registration.jsp?passwordsDifferent=true";
    String REGISTRATION_FAIL_USER_EXIST = "/WEB-INF/common/registration.jsp?alreadyExist=true";
    String REGISTRATION_SUCCESS = "/WEB-INF/common/registration.jsp?success=true";

    String SHOW_REPORTS = "/WEB-INF/client/show-reports.jsp";
    String SUBMIT_REPORT = "/WEB-INF/client/submit-report.jsp";
    String CHECK_REPORT = "/WEB-INF/inspector/check-report.jsp";
    String EDIT_REPORT = "/WEB-INF/client/edit-report.jsp";
    String SET_TAXABLE_ITEMS = "/WEB-INF/inspector/set-taxable.jsp";
    String SUBMIT_COMPLAINT =  "/WEB-INF/client/submit-complaint.jsp";

    String HOME = "/WEB-INF/common/welcome.jsp";
    String TO_LOGIN = "/WEB-INF/common/login.jsp";
    String TO_REGISTRATION = "/WEB-INF/common/registration.jsp";


}
