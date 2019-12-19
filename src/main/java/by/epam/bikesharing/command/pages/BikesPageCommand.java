package by.epam.bikesharing.command.pages;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.service.pages.BikesPageLogic;

import javax.servlet.http.HttpServletRequest;

public class BikesPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
//        int itemsCount = 15;
//        int pagesCount = (int) Math.ceil(bikes.size() / (float)itemsCount);
//        request.setAttribute("pageCount", pagesCount);
//
//        int currentPage = Integer.parseInt(request.getParameter("current_page"));
//        int startIndex = (currentPage - 1) * itemsCount;
//        int endIndex = currentPage * itemsCount - 1;
//        if (endIndex >= bikes.size())
//            endIndex = bikes.size() - 1;
//        request.setAttribute("startIndex", startIndex);
//        request.setAttribute("endIndex", endIndex);
        new BikesPageLogic().handleBikesPageRequest(request);
        return ConfigurationManager.getProperty("path.page.bikes");
    }
}