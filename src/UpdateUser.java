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
        
    }
}
