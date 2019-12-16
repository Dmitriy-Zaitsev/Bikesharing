package by.epam.bikesharing.command;

import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.resource.MessageManager;
import by.epam.bikesharing.resource.StringManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LocalizationCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String locale = (String) request.getParameter("language");
        MessageManager.setLocale(locale);
        StringManager.INSTANCE.setLocale(locale);
        //todo set user locale
        return ConfigurationManager.getProperty("path.page.main");
    }
}