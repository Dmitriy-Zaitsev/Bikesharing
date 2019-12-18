package by.epam.bikesharing.service.pages;

import by.epam.bikesharing.constant.ParameterValue;
import by.epam.bikesharing.dao.UserDao;
import by.epam.bikesharing.entity.User;

public class ProfilePageLogic {

    public User getUserProfile(User requestingUser, String requestedUserLogin) {
        User user;
        UserDao dao = new UserDao();
        user = (requestedUserLogin != null && ParameterValue.ROLE_ADMIN.equals(requestingUser.getRole())) ?
                dao.findUserByLogin(requestedUserLogin) :
                dao.findEntityById(requestingUser.getId());
        dao.closeConnection();
        return user;
    }
}