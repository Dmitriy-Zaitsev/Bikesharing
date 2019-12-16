package by.epam.bikesharing.command;

import by.epam.bikesharing.dao.UserDao;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.logic.PasswordHash;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class VerifyCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_ROLE = "role";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String enteredCode = request.getParameter("code");
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(PARAM_NAME_ROLE).equals("guest")) {
            String sendedCode = (String) session.getAttribute("code");
            if (enteredCode.equals(sendedCode)) {
                User user = new User();
                user.setLogin((String) session.getAttribute(PARAM_NAME_LOGIN));
                user.setEmail((String) session.getAttribute(PARAM_NAME_EMAIL));
                user.setRole("user");
                user.setPasswordHash((PasswordHash) session.getAttribute("hash"));
                UserDao userDAO = new UserDao();
                userDAO.create(user);
                page = ConfigurationManager.getProperty("path.page.index");
            } else {
                page = ConfigurationManager.getProperty("path.page.verification");
            }
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}