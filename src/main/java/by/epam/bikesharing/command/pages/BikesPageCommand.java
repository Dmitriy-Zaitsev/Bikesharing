package by.epam.bikesharing.command.pages;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.entity.Bike;
import by.epam.bikesharing.entity.BikeModel;
import by.epam.bikesharing.entity.Spot;
import by.epam.bikesharing.service.pages.PagesLogic;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BikesPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        PagesLogic logic = new PagesLogic();
        List<Bike> bikes = logic.searchBikes();
        List<BikeModel> models = logic.searchModels();
        List<Spot> spots = logic.searchSpots();
        List<String> spotNames = logic.getSpotNames();
        List<String> userNames = logic.getUserNames();
        request.setAttribute("bikes", bikes);
        request.setAttribute("models", models);
        request.setAttribute("spots", spots);
        request.setAttribute("spotNames", spotNames);
        request.setAttribute("userNames", userNames);

        int itemsCount = 15;
        int pagesCount = (int) Math.ceil(bikes.size() / (float)itemsCount);
        request.setAttribute("pageCount", pagesCount);

        int currentPage = Integer.parseInt(request.getParameter("current_page"));
        int startIndex = (currentPage - 1) * itemsCount;
        int endIndex = currentPage * itemsCount - 1;
        if (endIndex >= bikes.size())
            endIndex = bikes.size() - 1;
        request.setAttribute("startIndex", startIndex);
        request.setAttribute("endIndex", endIndex);

        return ConfigurationManager.getProperty("path.page.bikes");
    }
}