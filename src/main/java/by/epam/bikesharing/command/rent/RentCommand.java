package by.epam.bikesharing.command.rent;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.exception.TransactionException;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.service.rent.RentStartedTransaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RentCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterName.USER);
        long userId = user.getId();
        long bikeId = Long.parseLong(request.getParameter(ParameterName.BIKE_ID));
        try {
            session.setAttribute(ParameterName.USER, new RentStartedTransaction().assignBikeToUser(userId, bikeId));
            page = ConfigurationManager.getProperty("path.page.ride");
        } catch (TransactionException e) {
            page = ConfigurationManager.getProperty("path.page.main");
            //TODO message
            e.printStackTrace();
        }
        return page;
    }
}