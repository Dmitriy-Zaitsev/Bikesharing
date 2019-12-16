package by.epam.bikesharing.command;

import by.epam.bikesharing.logic.BikeModelLogic;
import by.epam.bikesharing.logic.pages.PagesLogic;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ChangeCostCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        String cost = request.getParameter("cost");
        new BikeModelLogic().editModelCost(Long.parseLong(id), cost);
        request.setAttribute("models", new PagesLogic().searchModels());
        return ConfigurationManager.getProperty("path.page.models");
    }
}