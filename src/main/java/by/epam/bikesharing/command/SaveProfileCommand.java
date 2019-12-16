package by.epam.bikesharing.command;

import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.logic.ProfileLogic;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SaveProfileCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        ProfileLogic logic = setupLogic(request, user);
        String message = logic.updateProfile();
        if (ProfileLogic.SUCCESS_UPDATE.equals(message)) {
            user = logic.getNewUser();
            session.setAttribute("user", user);
        } else {
            request.setAttribute("message", message);
        }
        request.setAttribute("profile", user);
        return ConfigurationManager.getProperty("path.page.profile");
    }

    private ProfileLogic setupLogic(HttpServletRequest request, User user) {
        ProfileLogic logic = new ProfileLogic(user);
        logic.setLogin(request.getParameter("login"));
        logic.setEmail(request.getParameter("email"));
        logic.setOldPassword(request.getParameter("old_password"));
        logic.setNewPassword(request.getParameter("new_password"));
        logic.setRepeatPassword(request.getParameter("repeat_password"));
        if ("true".equals(request.getParameter("update_image"))) {
            logic.setImage(request.getParameter("image"));
        }
        return logic;
    }
}