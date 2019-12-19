package by.epam.bikesharing.command.login;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.exception.IncorrectPasswordException;
import by.epam.bikesharing.exception.NoSuchUserException;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.resource.MessageManager;
import by.epam.bikesharing.service.LoginLogic;
import by.epam.bikesharing.service.pages.MainRentPageLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(ParameterName.LOGIN);
        String password = request.getParameter(ParameterName.PASSWORD);
        LoginLogic logic = new LoginLogic();
        HttpSession session = request.getSession(false);
        try {
            User user = logic.loginUser(login, password);
            if (session != null) {
                session.invalidate();
            }
            session = request.getSession(true);
            session.setAttribute(ParameterName.USER, user);
            session.setAttribute(ParameterName.LOCALE, user.getLocale());
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