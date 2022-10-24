import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateUser {

    private static final String UPDATE_EMAIL =
            "UPDATE users " +
                    "SET email = ? " +
                    "WHERE id = ?";
    private static final String UPDATE_PASSWORD =
            "UPDATE users " +
                    "SET password = ? " +
                    "WHERE id = ?";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("User ID: ");
        String userIdStr = scanner.nextLine();

        if (!Validator.isInteger(userIdStr)) {
            System.out.println(Constants.INVALID_USER_ID);
            return;
        }
        int userId = Integer.parseInt(userIdStr);

        System.out.print("New Email: ");
        String email = scanner.nextLine();

        if (!Validator.isValidEmail(email)){
            System.out.println(Constants.INVALID_EMAIL);
            return;
        }

        try {
            Connection connection = Utils.getSQLConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_EMAIL);
            statement.setString(1,email);
            statement.setInt(2,userId);

            final int updated = statement.executeUpdate();

            if (updated == 0){
                System.out.println(Constants.EMAIL_NOT_UPDATED);
                connection.close();
                return;
            }
            System.out.println(Constants.EMAIL_SUCCESSFULLY_UPDATED);
            connection.close();

        }catch (SQLException sqlException){
            System.out.println("Error! " + sqlException.getMessage());
        }

    }
}
