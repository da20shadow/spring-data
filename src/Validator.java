import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Validator {
    ;

    static boolean isInteger(String input){
        try {
            int number = Integer.parseInt(input);
            return true;
        }catch (NumberFormatException exception){
            return false;
        }
    }

    static boolean isValidUsername(String command) {
        Pattern usernamePattern =
                Pattern.compile(Constants.USERNAME_PATTERN,Pattern.CASE_INSENSITIVE);
        Matcher matcher = usernamePattern.matcher(command);
        return matcher.find();
    }

    static boolean isValidEmail(String command) {
        Pattern emailPattern =
                Pattern.compile(Constants.EMAIL_PATTERN,Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(command);
        return matcher.find();
    }
}
