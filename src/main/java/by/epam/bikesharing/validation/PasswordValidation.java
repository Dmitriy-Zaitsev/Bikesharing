package by.epam.bikesharing.validation;

public class PasswordValidation {
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    public static boolean isValid(String password) {
        return password.matches(PASSWORD_REGEX);
    }
}