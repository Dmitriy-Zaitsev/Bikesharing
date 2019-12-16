package by.epam.bikesharing.command;

import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.exception.IncorrectPasswordException;
import by.epam.bikesharing.exception.NoSuchUserException;
import by.epam.bikesharing.logic.LoginLogic;
import by.epam.bikesharing.logic.rent.MainRentPageLogic;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.resource.MessageManager;
import by.epam.bikesharing.resource.StringManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.apache.logging.log4j.web.WebLoggerContextUtils.getServletContext;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        LoginLogic logic = new LoginLogic();
        getServletContext().setAttribute("stringManager", StringManager.INSTANCE);
        try {
            User user = logic.loginUser(login, password);
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            session = request.getSession(true);
            session.setAttribute("user", user);
            page = new MainRentPageLogic().getUserMainPage(user, request);
        } catch (IncorrectPasswordException e) {
            e.printStackTrace();
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        } catch (NoSuchUserException e) {
            e.printStackTrace();
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}