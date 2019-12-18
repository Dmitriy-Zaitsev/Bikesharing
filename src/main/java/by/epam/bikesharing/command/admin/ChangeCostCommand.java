package by.epam.bikesharing.command.admin;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.service.BikeModelLogic;
import by.epam.bikesharing.service.pages.PagesLogic;
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