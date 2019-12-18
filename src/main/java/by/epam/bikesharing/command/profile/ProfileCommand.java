package by.epam.bikesharing.command.profile;

import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.service.pages.PagesLogic;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ProfileCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String login = request.getParameter("profile_name");
        User userProfile = new PagesLogic().getUserProfile(user, login);
        request.setAttribute("profile", userProfile);
        return ConfigurationManager.getProperty("path.page.profile");
    }
}