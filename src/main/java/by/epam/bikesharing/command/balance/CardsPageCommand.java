package by.epam.bikesharing.command.balance;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.dao.CardDao;
import by.epam.bikesharing.entity.Card;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CardsPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Card> cards = new CardDao().findUserCards(user.getId());
        request.setAttribute("cards", cards);
        page = ConfigurationManager.getProperty("path.page.cards");
        return page;
    }
}