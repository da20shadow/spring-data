import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InsertUser {

    private static final String INSERT_USER = "CALL insertUser(?,?,?);";

    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        if (!isUsernameValid(username)) {
            System.out.println(Constants.INVALID_USERNAME);
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();


        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();

        if (!isEmailValid(email)) {
            System.out.println(Constants.INVALID_EMAIL);
            return;
        }

        PreparedStatement insertStatement = connection.prepareStatement(INSERT_USER);

        insertStatement.setString(1,username);
        insertStatement.setString(2,password);
        insertStatement.setString(3,email);

        try {
            ResultSet resultSet = insertStatement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("Error! Please, try again!");
                connection.close();
                return;
            }

            String registeredUsername = resultSet.getString(Constants.COLUMN_LABEL_USERNAME);
            String registeredEmail = resultSet.getString(Constants.COLUMN_LABEL_EMAIL);
            System.out.printf("Successfully Registered User: %s with Email: %s",
                    registeredUsername, registeredEmail);

        }catch (SQLException sqlException){
            if (sqlException.getErrorCode() == 1062){
                System.out.println("Such user already exist!");
            }
        }
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