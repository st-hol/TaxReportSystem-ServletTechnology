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
import java.util.Objects;
import java.util.Optional;


/**
 * Processes registration.
 *
 * @author Stanislav Holovachuk
 */

public class Registration implements Command {
    private static final Logger logger = LogManager.getLogger(Registration.class);

    private UserService userService;

    public Registration() {
        this.userService = UserService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String email = request.getParameter("email");
        final String role = request.getParameter("role");
        final String password = request.getParameter("password");
        final String confirmPassword = request.getParameter("confirmPassword");
        final String firstName = request.getParameter("firstName");
        final String lastName = request.getParameter("lastName");

        if (!(UserValidator.validateEmail(email) && UserValidator.validatePassword(password))) {
            logger.info("User [" + email + "]" + " entered invalid data.");
            return "/WEB-INF/common/registration.jsp?dataInvalid=true";
        }


        if (!password.equals(confirmPassword)) {
            logger.info("User [" + email + "]" + " password and its confirmation are not equal.");
            return "/WEB-INF/common/registration.jsp?passwordsDifferent=true";
        }

        User.ROLE userRole = User.ROLE.valueOf(role);

        final User user = new User();
        user.setRole(userRole);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        try {
            userService.registerUserAccount(user);
        } catch (AlreadyExistingDBRecordException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            return "/WEB-INF/common/registration.jsp?alreadyExist=true";
        }

        //assigning random inspector to new user
        if (userRole.equals(User.ROLE.CLIENT)) {
            userService.assignRandomInspectorToClient(user);
        }

        logger.info("User [" + email + "]" + " role[" + role + "]" + " successfully registered.");
        return "/WEB-INF/common/registration.jsp?success=true";
    }
}
