package by.epam.bikesharing.command;

import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.exception.TransactionException;
import by.epam.bikesharing.logic.rent.RentStartedTransaction;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RentCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        long userId = user.getId();
        long bikeId = Long.parseLong(request.getParameter("bike_id"));
        try {
            session.setAttribute("user", new RentStartedTransaction().assignBikeToUser(userId, bikeId));
            page = ConfigurationManager.getProperty("path.page.ride");
        } catch (TransactionException e) {
            page = ConfigurationManager.getProperty("path.page.main");
            //TODO message
            e.printStackTrace();
        }
        return page;
    }
}