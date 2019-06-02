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

/**
 * Processes logging in.
 *
 * @author Stanislav Holovachuk
 */
public class Login implements Command {

    private static final Logger logger = LogManager.getLogger(Login.class);
    private UserService userService;

    public Login(UserService studentService) {
        this.userService = studentService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        final String email = request.getParameter("email");
        final String password = request.getParameter("password");

        if (!(UserValidator.validateEmail(email) && UserValidator.validatePassword(password))) {
            logger.info("User [" + email + "]" + " entered wrong data.");
            return "/WEB-INF/common/login.jsp?dataInvalid=true";
        }


        if (userService.isExistingUser(email, password)) {
            //in order to prevent logging into one account at the same time
            if (CommandUtility.checkUserIsLogged(request, email)) {
                return "/WEB-INF/common/error/multilogin.jsp";
            }

            final User.ROLE role = userService.getRoleByEmailAndPass(email, password);

            CommandUtility.logUser(request, email, password, role);
            logger.info("User [" + email + "] role [" + role + "] signed in successfully.");

        } else {
            logger.info("Invalid attempt of login user: [" + email + "]");
            request.getSession().setAttribute("role", User.ROLE.UNKNOWN);
        }

        String path = request.getServletContext().getContextPath();
        return "redirect@" + path + "/app/personal-cabinet";
    }
}




//        Command personalCabinet = new PersonalCabinet();
//        return personalCabinet.execute(request, response);
