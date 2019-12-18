package by.epam.bikesharing.command.signup;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.service.PasswordHash;
import by.epam.bikesharing.service.email.EmailSender;
import by.epam.bikesharing.service.email.VerificationCode;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_ROLE = "role";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(PARAM_NAME_ROLE).equals("guest")) {
            String code = (new VerificationCode()).getCode(6);
            PasswordHash passwordHash = new PasswordHash(password);
            session.setAttribute(PARAM_NAME_LOGIN, login);
            session.setAttribute(PARAM_NAME_EMAIL, email);
            session.setAttribute("hash", passwordHash);
            session.setAttribute("code", code);
            EmailSender emailSender = new EmailSender(email);
            emailSender.sendVerificationCode(code);
            page = ConfigurationManager.getProperty("path.page.verification");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}