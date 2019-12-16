package by.epam.bikesharing.validation;

public class CvvValidation {
    private static final String CVV_REGEX = "^[0-9]{3,4}$";

    public static boolean isValid(String cvv) {
        return cvv.matches(CVV_REGEX);
    }
}
