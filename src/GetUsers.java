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
            }else {
                System.out.println("Finished!");
                connection.close();
                return;
            }
        }

    }



}
