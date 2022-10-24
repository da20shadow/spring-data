public enum Constants {
    ;
    static final String USER_KEY = "user";
    static final String USER_VALUE = "root";

    static final String PASSWORD_KEY = "password";
    static final String PASSWORD_VALUE = "";

    static final String JDBC_URL = "jdbc:mysql://localhost:3306/demo";

    /* ----COLUMN LABELS---- */
    static final String COLUMN_LABEL_USERNAME = "username";
    static final String COLUMN_LABEL_EMAIL = "email";

    /* ---FORMAT MESSAGES--- */
    static final String NO_USER_FORMAT = "No user with ID: %d!!!%n";
    static final String USERS_FORMAT = "Name: %s, Email: %s%n";
}