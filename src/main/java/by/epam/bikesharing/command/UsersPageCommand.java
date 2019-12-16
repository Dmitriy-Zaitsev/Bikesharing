package by.epam.bikesharing.command;

import by.epam.bikesharing.dao.UserDao;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UsersPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        UserDao userDao = new UserDao();
        List<User> users = userDao.findAll();
        userDao.closeConnection();
        request.setAttribute("users", users);

        return ConfigurationManager.getProperty("path.page.users");
    }
}
