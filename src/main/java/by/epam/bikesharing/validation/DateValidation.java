package by.epam.bikesharing.validation;

public class DateValidation {
    private static final String DATE_REGEX = "\\d{2}";

    public static boolean isValidYear(String cvv) {
        return cvv.matches(DATE_REGEX);
    }

    public static boolean isValidMonth(String month) {
        if (month.matches(DATE_REGEX)) {
            int intMonth = Integer.parseInt(month);
            return (intMonth > 0 && intMonth < 13);
        }
        return false;
    }
}