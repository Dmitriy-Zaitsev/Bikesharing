package by.epam.bikesharing.command.admin;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.dao.BikeDao;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class SearchCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        BikeDao bikeDao = new BikeDao();
        request.setAttribute("bikes", bikeDao.findAll());
        page = ConfigurationManager.getProperty("path.page.bikes");
        return page;
    }
}