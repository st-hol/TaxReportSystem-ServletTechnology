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

import static ua.training.controller.command.TextConstants.Parameters.*;
import static ua.training.controller.command.TextConstants.Routes.*;


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

        final String email = request.getParameter(EMAIL);
        final String role = request.getParameter(ROLE);
        final String password = request.getParameter(PASSWORD);
        final String confirmPassword = request.getParameter(CONFIRM_PASSWORD);
        final String firstName = request.getParameter(FIRST_NAME);
        final String lastName = request.getParameter(LAST_NAME);

        if (!(UserValidator.validateEmail(email) && UserValidator.validatePassword(password))) {
            logger.info("User [" + email + "]" + " entered invalid data.");
            return REGISTRATION_FAIL_INVALID_DATA;
        }

        if (!password.equals(confirmPassword)) {
            logger.info("User [" + email + "]" + " password and its confirmation are not equal.");
            return REGISTRATION_FAIL_PASSWORDS_DIFFERENT;
        }

        User.ROLE userRole = User.ROLE.valueOf(role);
        User user = accomplishNewUser(userRole, email, password, firstName, lastName);

        try {
            userService.registerUserAccount(user);
        } catch (AlreadyExistingDBRecordException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            return REGISTRATION_FAIL_USER_EXIST;
        }

        //assigning random inspector to new user
        if (userRole.equals(User.ROLE.CLIENT)) {
            userService.assignRandomInspectorToClient(user);
        }

        logger.info("User [" + email + "]" + " role[" + role + "]" + " successfully registered.");
        return REGISTRATION_SUCCESS;
    }



    private User accomplishNewUser(User.ROLE  userRole, String email, String password, String firstName, String lastName){
        final User user = new User();
        user.setRole(userRole);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        return user;
    }

}
