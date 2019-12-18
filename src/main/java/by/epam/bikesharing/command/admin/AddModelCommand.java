package by.epam.bikesharing.command.admin;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.constant.ParameterValue;
import by.epam.bikesharing.service.BikeModelLogic;
import by.epam.bikesharing.service.pages.PagesLogic;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class AddModelCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String cost = request.getParameter(ParameterName.COST);
        String action = request.getParameter(ParameterName.ACTION);
        if (ParameterValue.ACTION_ADD.equals(action)) {
            String name = request.getParameter(ParameterName.NAME);
            String image = request.getParameter(ParameterName.IMAGE);
            new BikeModelLogic().addModel(name, cost, image);
        } else if (ParameterValue.ACTION_EDIT.equals(action)) {
            String id = request.getParameter(ParameterName.ID);
            new BikeModelLogic().editModelCost(Long.parseLong(id), cost);
        }
        request.setAttribute(ParameterName.MODELS, new PagesLogic().searchModels());
        return ConfigurationManager.getProperty("path.page.models");
    }
}