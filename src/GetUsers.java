import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetUsers {

    private static final String GET_ALL_USERS =
            "SELECT username,email,first_name,last_name " +
            "FROM users";
    private static final String GET_USER_BY_ID =
            "SELECT username,email,first_name,last_name " +
                    "FROM users " +
                    "WHERE id = ?";
    private static final String GET_USER_BY_USERNAME =
            "SELECT username,email,first_name,last_name " +
                    "FROM users " +
                    "WHERE username = ?";
    private static final String GET_USER_BY_EMAIL =
            "SELECT username,email,first_name,last_name " +
                    "FROM users " +
                    "WHERE email = ?";


    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){

            String command = scanner.nextLine();

            if (command.equals("all")){
                getAllUsers(connection);
            }else if (isInteger(command)){
                int user_id = Integer.parseInt(command);
                getUserById(connection,user_id);
            }else if (isValidEmail(command)){
                String email = command;
                getUserByEmail(connection,email);
            }else {
                System.out.println("Finished!");
                connection.close();
                return;
            }
        }

    }

    private static void getAllUsers(Connection connection) throws SQLException {

        final PreparedStatement statement =
                connection.prepareStatement(GET_ALL_USERS);
        final ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){

            String name = resultSet.getString(Constants.COLUMN_LABEL_USERNAME);
            String email = resultSet.getString(Constants.COLUMN_LABEL_EMAIL);

            System.out.printf(Constants.USERS_FORMAT,name,email);
        }
    }

    private static void getUserById(Connection connection, int user_id) throws SQLException {

        final PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID);

        statement.setInt(1,user_id);

        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            System.out.printf(Constants.NO_USER_FORMAT,user_id);
            return;
        }

        String name = resultSet.getString(Constants.COLUMN_LABEL_USERNAME);
        String email = resultSet.getString(Constants.COLUMN_LABEL_EMAIL);

        System.out.printf(Constants.USERS_FORMAT,name,email);
    }

    private static boolean isInteger(String input){
        try {
            int number = Integer.parseInt(input);
            return true;
        }catch (NumberFormatException exception){
            return false;
        }
    }

    private static boolean isValidEmail(String command) {
        Pattern emailPattern = Pattern.compile("^[a-z0-9_]+[@][a-z][.com]$",Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(command);
        return matcher.find();
    }
}
