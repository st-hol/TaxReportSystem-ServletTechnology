package ua.training.model.validator;

import java.util.regex.Pattern;

public class NumberValidator {

    public static boolean validateNumber(String value) {
        return value != null && Pattern.matches(Regexes.NUMBER_REGEXP.getREGEXP(), value);
    }

    public static boolean validateExamScore(String value) {

        try {
            double dValue = Double.parseDouble(value);
            if (dValue < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        return Pattern.matches(Regexes.EXAM_SCORE_REGEXP.getREGEXP(), value);
    }
}

