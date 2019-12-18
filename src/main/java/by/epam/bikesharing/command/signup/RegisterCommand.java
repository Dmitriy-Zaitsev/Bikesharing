package by.epam.bikesharing.command.signup;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.constant.ParameterValue;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.resource.MessageManager;
import by.epam.bikesharing.service.PasswordHash;
import by.epam.bikesharing.service.email.EmailSender;
import by.epam.bikesharing.service.email.VerificationCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(ParameterName.LOGIN);
        String password = request.getParameter(ParameterName.PASSWORD);
        String email = request.getParameter(ParameterName.EMAIL);
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(ParameterName.ROLE).equals(ParameterValue.ROLE_GUEST)) {
            String verificationCode = (new VerificationCode()).getCode(6);
            PasswordHash passwordHash = new PasswordHash(password);
            session.setAttribute(ParameterName.LOGIN, login);
            session.setAttribute(ParameterName.EMAIL, email);
            session.setAttribute(ParameterName.HASH, passwordHash);
            session.setAttribute(ParameterName.CODE, verificationCode);
            EmailSender emailSender = new EmailSender(email);
            emailSender.sendVerificationCode(verificationCode);
            page = ConfigurationManager.getProperty("path.page.verification");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}