package by.epam.bikesharing.command.rent;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.exception.TransactionException;
import by.epam.bikesharing.service.rent.MainRentPageLogic;
import by.epam.bikesharing.service.rent.RentFinishedTransaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FinishRentCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            user = new RentFinishedTransaction().finishRent(user.getId(), user.getBikeId(), 8);
            session.setAttribute("user", user);
        } catch (TransactionException e) {
            //TODO
            e.printStackTrace();
        }
        page = new MainRentPageLogic().getUserMainPage(user, request);
        return page;
    }
}