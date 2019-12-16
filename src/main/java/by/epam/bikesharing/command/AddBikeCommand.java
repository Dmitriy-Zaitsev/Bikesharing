package by.epam.bikesharing.command;

import by.epam.bikesharing.entity.Bike;
import by.epam.bikesharing.entity.BikeModel;
import by.epam.bikesharing.entity.Spot;
import by.epam.bikesharing.logic.BikeLogic;
import by.epam.bikesharing.logic.pages.PagesLogic;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddBikeCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String spot = request.getParameter("spotSelect");
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String serialNumber = request.getParameter("serialNumber");
            String model = request.getParameter("modelSelect");
            //String modelId = request.getParameter("model_id");
            new BikeLogic().addBike(serialNumber, model, spot);
        } else if ("edit".equals(action)) {
            String bikeId = request.getParameter("id");
            new BikeLogic().editBikeSpot(Long.parseLong(bikeId), spot);
        }
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
        return ConfigurationManager.getProperty("path.page.bikes");
    }
}