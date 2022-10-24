import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InsertUser {

    private static final String INSERT_USER = "CALL insertUser(?,?,?);";

    public static void main(String[] args) {

    }

    private static boolean isUsernameValid(String username){
        username = username.trim();

        if (username.isEmpty()){
            return false;
        }
        Pattern usernamePattern = Pattern.compile(Constants.USERNAME_PATTERN,Pattern.CASE_INSENSITIVE);
        Matcher matcher = usernamePattern.matcher(username);

        return matcher.find();
    }

    private static boolean isEmailValid(String email){
        email = email.trim();

        if (email.isEmpty()){
            return false;
        }
        Pattern emailPattern = Pattern.compile(Constants.EMAIL_PATTERN,Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);

        return matcher.find();
    }
}