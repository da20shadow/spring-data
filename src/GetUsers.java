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

}
