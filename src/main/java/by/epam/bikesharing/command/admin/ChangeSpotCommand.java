package by.epam.bikesharing.command.admin;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.service.BikeLogic;
import by.epam.bikesharing.service.pages.PagesLogic;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ChangeSpotCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String bikeId = request.getParameter("id");
        String spotId = request.getParameter("spotId");
        new BikeLogic().editBikeSpot(Long.parseLong(bikeId), spotId);
        request.setAttribute("bikes", new PagesLogic().searchBikes());
        return ConfigurationManager.getProperty("path.page.bikes");
    }
}