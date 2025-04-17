package np.com.debid.restrouserservice.constant;

public class Constant {
    public static class Messages {
        public static final String LOGIN_SUCCESS = "Login successful.";
        public static final String USER_CREATED = "User created successfully.";
        public static final String EMAIL_IS_ALREADY_TAKEN = "Error: Email is already taken!.";
        public static final String USERNAME_IS_ALREADY_TAKEN = "Error: Username is already taken!.";
        public static final String ROLE_NOT_FOUND = "Error: Role is not found.";
    }

    public static class ErrorCodes {
        public static final int EMAIL_IS_ALREADY_TAKEN_ERROR_CODE = 1001;
        public static final int USERNAME_IS_ALREADY_TAKEN_ERROR_CODE = 1002;
        public static final int ROLE_NOT_FOUND_ERROR_CODE = 1003;
    }
}
