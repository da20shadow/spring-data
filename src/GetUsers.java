import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

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
            }else if (Validator.isInteger(command)){
                int user_id = Integer.parseInt(command);
                getUserById(connection,user_id);
            }else if (Validator.isValidUsername(command)){
                getUserByUsername(connection,command);
            }else if (Validator.isValidEmail(command)){
                getUserByEmail(connection,command);
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
            System.out.printf(Constants.NO_USER_FORMAT_BY_ID,user_id);
            return;
        }

        String name = resultSet.getString(Constants.COLUMN_LABEL_USERNAME);
        String email = resultSet.getString(Constants.COLUMN_LABEL_EMAIL);

        System.out.printf(Constants.USERS_FORMAT,name,email);
    }

    private static void getUserByUsername(Connection connection, String username) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(GET_USER_BY_USERNAME);
        statement.setString(1,username);
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            System.out.printf(Constants.NO_USER_FORMAT_BY_USERNAME,username);
            return;
        }
        String name = resultSet.getString(Constants.COLUMN_LABEL_USERNAME);
        String email = resultSet.getString(Constants.COLUMN_LABEL_EMAIL);

        System.out.printf(Constants.USERS_FORMAT,name,email);
    }

    private static void getUserByEmail(Connection connection, String email) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(GET_USER_BY_EMAIL);
        statement.setString(1,email);
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            System.out.printf(Constants.NO_USER_FORMAT_BY_EMAIL,email);
            return;
        }
        String username = resultSet.getString(Constants.COLUMN_LABEL_USERNAME);
        String userEmail = resultSet.getString(Constants.COLUMN_LABEL_EMAIL);
        System.out.printf(Constants.USERS_FORMAT,username,userEmail);
    }

}
