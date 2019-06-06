package ua.training.model.validator;



import java.util.regex.Pattern;

import static ua.training.model.validator.Regexes.EMAIL_REGEXP;
import static ua.training.model.validator.Regexes.PASSWORD_REGEXP;


public class UserValidator {

    public static boolean validateEmail(String emailValue) {
        return emailValue != null && Pattern.matches(EMAIL_REGEXP, emailValue);
    }

    public static boolean validatePassword(String passwordValue) {
        return passwordValue != null && Pattern.matches(PASSWORD_REGEXP, passwordValue);
    }

}