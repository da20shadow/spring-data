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
    static final String NO_USER_FORMAT_BY_ID = "No user with ID: %d!!!%n";
    static final String NO_USER_FORMAT_BY_EMAIL = "No user with Email: %s!!!%n";
    static final String NO_USER_FORMAT_BY_USERNAME = "No user with username: %s!%n";
    static final String USERS_FORMAT = "Name: %s, Email: %s%n";

    /* ---- ERROR MESSAGES ---- */
    static final String INVALID_USERNAME = "Invalid username! Username must be between 5 - 45 characters long " +
            "and can only contains letters '_' and digits!";
    static final String INVALID_EMAIL = "Invalid email!";

    /* REGEX PATTERNS */
    static final String USERNAME_PATTERN = "^[a-z1-9_]{5,45}$";
    static final String EMAIL_PATTERN = "^[a-z1-9_]+[@][a-z]+[.com]+$";
}
