package by.epam.bikesharing.command;

import by.epam.bikesharing.logic.BikeModelLogic;
import by.epam.bikesharing.logic.pages.PagesLogic;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class AddModelCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String cost = request.getParameter("cost");
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String name = request.getParameter("name");
            String image = request.getParameter("image_url");
            new BikeModelLogic().addModel(name, cost, image);
        } else if ("edit".equals(action)) {
            String id = request.getParameter("id");
            new BikeModelLogic().editModelCost(Long.parseLong(id), cost);
        }
        request.setAttribute("models", new PagesLogic().searchModels());
        return ConfigurationManager.getProperty("path.page.models");
    }
}