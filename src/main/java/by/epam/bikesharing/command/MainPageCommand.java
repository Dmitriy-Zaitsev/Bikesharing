package by.epam.bikesharing.command;

import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.logic.rent.MainRentPageLogic;

import javax.servlet.http.HttpServletRequest;

public class MainPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return new MainRentPageLogic().getUserMainPage(user, request);
    }
}