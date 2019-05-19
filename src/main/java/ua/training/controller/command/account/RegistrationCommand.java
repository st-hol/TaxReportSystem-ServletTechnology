package ua.training.controller.command.account;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.entity.User;
import ua.training.model.exception.AlreadyExistingDBRecordException;
import ua.training.model.service.UserService;
import ua.training.model.validator.UserValidator;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Processes registration.
 *
 * @author Stanislav Holovachuk
 */

//todo fix hangs on reg client
public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);

    private UserService userService;


    public RegistrationCommand(UserService studentService) {
        this.userService = studentService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String email = request.getParameter("email");
        final String role = request.getParameter("role");
        final String password = request.getParameter("password");
        final String confirmPassword = request.getParameter("confirmPassword");
        final String firstName = request.getParameter("firstName");
        final String lastName = request.getParameter("lastName");

        if (!password.equals(confirmPassword)) {
            logger.info("User [" + email + "]" + " password and its confirmation is not equal.");
            return "/WEB-INF/common/registration.jsp?passwordsDifferent=true";
        }

        if (!(UserValidator.validateEmail(email) && UserValidator.validatePassword(password))) {
            logger.info("User [" + email + "]" + " entered wrong data.");
            return "/WEB-INF/common/registration.jsp?dataInvalid=true";
        }

        User.ROLE userRole = User.ROLE.valueOf(role);

        final User user = new User();
        user.setRole(userRole);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
//        user.setAssignedInspector(noInspector);

        try {
            userService.registerUserAccount(user);
        } catch (AlreadyExistingDBRecordException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            return "/WEB-INF/common/registration.jsp?success=false";
        }

        if (userRole.equals(User.ROLE.CLIENT)) {
            userService.assignRandomInspectorToClient(user);
        }

        logger.info("User [" + email + "]" + " role[" + role + "]" + " successfully registered.");
        return "/WEB-INF/common/registration.jsp?success=true";
    }
}


//            return "redirect@" + path + "/jsp/registration.jsp?dataInvalid=true";
//return "redirect@" + path + "/jsp/registration.jsp?success=true";