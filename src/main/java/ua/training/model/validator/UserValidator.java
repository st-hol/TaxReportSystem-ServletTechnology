package ua.training.model.validator;



import java.util.regex.Pattern;

public class UserValidator {

    public static boolean validateEmail(String emailValue) {
        //System.out.println(emailValue != null && Pattern.matches(Regexes.EMAIL_REGEXP.getREGEXP(), emailValue));
        return emailValue != null && Pattern.matches(Regexes.EMAIL_REGEXP.getREGEXP(), emailValue);
    }

    public static boolean validatePassword(String passwordValue) {
        //System.out.println(passwordValue != null && Pattern.matches(Regexes.PASSWORD_REGEXP.getREGEXP(), passwordValue));
        return passwordValue != null && Pattern.matches(Regexes.PASSWORD_REGEXP.getREGEXP(), passwordValue);
    }

}