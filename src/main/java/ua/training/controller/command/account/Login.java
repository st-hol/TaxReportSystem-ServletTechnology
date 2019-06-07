package ua.training.controller.command.account;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.model.validator.UserValidator;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.training.controller.command.TextConstants.Parameters.*;
import static ua.training.controller.command.TextConstants.Routes.*;

/**
 * Processes logging in.
 *
 * @author Stanislav Holovachuk
 */
public class Login implements Command {

    private static final Logger logger = LogManager.getLogger(Login.class);
    private UserService userService;

    public Login() {
        this.userService = UserService.getInstance();
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        final String email = request.getParameter(EMAIL);
        final String password = request.getParameter(PASSWORD);

        if (!(UserValidator.validateEmail(email) && UserValidator.validatePassword(password))) {
            logger.info("User [" + email + "]" + " entered wrong data.");
            return LOGIN_FAIL_INVALID_INPUT;
        }

        if (userService.isExistingUser(email, password)) {
            //in order to prevent logging into one account at the same time
            if (CommandUtility.checkUserIsLogged(request, email)) {
                return MULTILOGIN_ERROR;
            }

            final User.ROLE role = userService.getRoleByEmailAndPass(email, password);
            CommandUtility.logUser(request, email, password, role);
            logger.info("User [" + email + "] role [" + role + "] signed in successfully.");
        } else {
            logger.info("Invalid attempt of login user: [" + email + "]");
            request.getSession().setAttribute(ROLE, User.ROLE.UNKNOWN);
            return USER_NOT_EXIST;
        }

        String path = request.getServletContext().getContextPath();
        return REDIRECT + path + TO_PERSONAL_CABINET;
    }

}
//        PersonalCabinet personalCabinet = new PersonalCabinet();
//        return personalCabinet.execute(request,response);

