package by.epam.bikesharing.command;

import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ChangeSpotCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String bikeId = request.getParameter("id");
        String spotId = request.getParameter("spotId");
        return ConfigurationManager.getProperty("path.page.bikes");
    }
}