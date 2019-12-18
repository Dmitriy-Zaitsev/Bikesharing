package by.epam.bikesharing.command.signup;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignupCommand implements ActionCommand {
    private static final String PARAM_NAME_ROLE = "role";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        session = request.getSession(true);
        session.setAttribute(PARAM_NAME_ROLE, "guest");
        return ConfigurationManager.getProperty("path.page.signup");
    }
}