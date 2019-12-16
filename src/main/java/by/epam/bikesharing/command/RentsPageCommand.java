package by.epam.bikesharing.command;

import by.epam.bikesharing.entity.Rent;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.logic.pages.PagesLogic;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RentsPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        long id = Long.parseLong(request.getParameter("id"));
        List<Rent> rents = new PagesLogic().getUserRents(user, id);
        request.setAttribute("rents", rents);
        return ConfigurationManager.getProperty("path.page.rents");
    }
}