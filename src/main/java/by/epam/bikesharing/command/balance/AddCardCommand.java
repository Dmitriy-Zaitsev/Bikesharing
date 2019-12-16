package by.epam.bikesharing.command.balance;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.dao.CardDao;
import by.epam.bikesharing.entity.Card;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.logic.PasswordHash;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddCardCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        Card card = new Card();
        card.setUserId(user.getId());
        card.setNumber(request.getParameter("number"));
        card.setFirstName(request.getParameter("firstName"));
        card.setLastName(request.getParameter("lastName"));
        card.setMonth(Integer.parseInt(request.getParameter("month")));
        card.setYear(Integer.parseInt(request.getParameter("year")));
        card.setCvv(new PasswordHash(request.getParameter("cvv")));
        CardDao cardDao = new CardDao();
        cardDao.create(card);
        cardDao.closeConnection();
        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}