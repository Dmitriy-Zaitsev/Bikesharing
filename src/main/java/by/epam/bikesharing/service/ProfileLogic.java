package by.epam.bikesharing.service;

import by.epam.bikesharing.dao.UserDao;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.resource.MessageManager;
import by.epam.bikesharing.validation.EmailValidatiom;
import by.epam.bikesharing.validation.LoginValidation;
import by.epam.bikesharing.validation.PasswordValidation;

public class ProfileLogic {
    private static final int MAX_FILE_SIZE = 350000;
    private static final String INVALID_EMAIL = MessageManager.getProperty("message.invalid_email");
    private static final String INVALID_IMAGE = MessageManager.getProperty("message.invalid_image");
    private static final String INVALID_LOGIN = MessageManager.getProperty("message.invalid_login");
    private static final String INVALID_PASSWORD = MessageManager.getProperty("message.invalid_password");
    private static final String LOGIN_IN_USE = MessageManager.getProperty("message.login_in_use");
    private static final String EMAIL_IN_USE = MessageManager.getProperty("message.email_in_use");
    private static final String SAME_PASSWORDS = MessageManager.getProperty("message.same_password");
    private static final String CONFIRM_PASSWORDS = MessageManager.getProperty("message.confirm_password");
    private static final String OLD_PASSWORD_WRONG = MessageManager.getProperty("message.wrong_password");
    public static final String SUCCESS_UPDATE = "OK";
    private User oldUser;
    private User newUser;
    private String login;
    private String email;
    private String oldPassword;
    private String newPassword;
    private String repeatPassword;
    private String image;

    public ProfileLogic(User oldUser) {
        this.oldUser = oldUser;
    }

    public String updateProfile() {
        String result = SUCCESS_UPDATE;
        newUser = new User(oldUser);
        if (!oldUser.getLogin().equals(login) && !login.isBlank()) {
            if (!SUCCESS_UPDATE.equals(result = updateLogin()))
                return result;
        }
        if (!oldUser.getEmail().equals(email) && !email.isBlank()) {
            if (!SUCCESS_UPDATE.equals(result = updateEmail()))
                return result;
        }
        if (image != null) {
            if (!updateImage())
                return INVALID_IMAGE;
        }
        if (!newPassword.isBlank()) {
            if (!SUCCESS_UPDATE.equals(result = updatePassword()))
                return result;
        }
        UserDao dao = new UserDao();
        dao.update(newUser);
        dao.updateUserImage(oldUser.getId(), newUser.getImage());
        dao.closeConnection();
        return result;
    }

    private String updateLogin() {
        if (!LoginValidation.isValid(login)) {
            return INVALID_LOGIN;
        }
        if (LoginValidation.isInUse(login)) {
            return LOGIN_IN_USE;
        }
        newUser.setLogin(login);
        return SUCCESS_UPDATE;
    }

    private String updateEmail() {
        if (!EmailValidatiom.isValid(email)) {
            return INVALID_EMAIL;
        }
        if (EmailValidatiom.isInUse(email)) {
            return EMAIL_IN_USE;
        }
        newUser.setEmail(email);
        return SUCCESS_UPDATE;
    }

    private boolean updateImage() {
        if (image.length() <= MAX_FILE_SIZE) {
            newUser.setImage(image);
            return true;
        }
        return false;
    }

    private String updatePassword() {
        if (!oldUser.getPasswordHash().isCorrectPassword(oldPassword)) {
            return OLD_PASSWORD_WRONG;
        }
        if (!PasswordValidation.isValid(newPassword)) {
            return INVALID_PASSWORD;
        }
        if (newPassword.equals(oldPassword)) {
            return SAME_PASSWORDS;
        }
        if (!newPassword.equals(repeatPassword)) {
            return CONFIRM_PASSWORDS;
        }
        newUser.setPasswordHash(new PasswordHash(newPassword));
        return SUCCESS_UPDATE;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public void setImage(String image) {
        this.image = image;
    }
}