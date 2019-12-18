package by.epam.bikesharing.command.signup;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.service.PasswordHash;
import by.epam.bikesharing.service.signup.SignupLogic;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class VerifyCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD_HASH = "hash";
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
                String userLogin = (String) session.getAttribute(PARAM_NAME_LOGIN);
                String userEmail = (String) session.getAttribute(PARAM_NAME_EMAIL);
                PasswordHash userHash = (PasswordHash) session.getAttribute(PARAM_NAME_PASSWORD_HASH);
                SignupLogic logic = new SignupLogic();
                logic.addUser(userLogin, userEmail, userHash);
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