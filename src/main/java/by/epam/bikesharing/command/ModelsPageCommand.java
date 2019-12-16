package by.epam.bikesharing.command;

import by.epam.bikesharing.entity.BikeModel;
import by.epam.bikesharing.logic.pages.PagesLogic;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ModelsPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        List<BikeModel> models = new PagesLogic().searchModels();
        request.setAttribute("models", models);
        return ConfigurationManager.getProperty("path.page.models");
    }
}