package by.epam.bikesharing.validation;

import by.epam.bikesharing.dao.UserDao;

public class LoginValidation {
    private static final String LOGIN_REGEX = "^[a-z0-9_-]{4,16}$";

    public static boolean isValid(String login) {
        return login.matches(LOGIN_REGEX);
    }

    public static boolean isInUse(String login) {
        UserDao dao = new UserDao();
        boolean result = (dao.findUserByLogin(login) != null);
        dao.closeConnection();
        return result;
    }
}