package by.epam.bikesharing.service.pages;

import by.epam.bikesharing.dao.*;
import by.epam.bikesharing.entity.*;

import java.util.List;

public class PagesLogic {

    public List<Bike> searchBikes() {
        BikeDao dao = new BikeDao();
        List<Bike> bikes = dao.findAll();
        dao.closeConnection();
        return bikes;
    }

    public List<BikeModel> searchModels() {
        ModelDao dao = new ModelDao();
        List<BikeModel> models = dao.findAll();
        dao.closeConnection();
        return models;
    }

    public List<Spot> searchSpots() {
        SpotDao dao = new SpotDao();
        List<Spot> spots = dao.findAll();
        dao.closeConnection();
        return spots;
    }

    public List<String> getSpotNames() {
        StringListDao dao = new StringListDao();
        List<String> names = dao.findBikesSpotNames();
        dao.closeConnection();
        return names;
    }

    public List<String> getUserNames() {
        StringListDao dao = new StringListDao();
        List<String> names = dao.findBikesUserNames();
        dao.closeConnection();
        return names;
    }

    public User getUserProfile(User requestingUser, String requestedUserLogin) {
        User user;
        UserDao dao = new UserDao();
        user = (requestedUserLogin != null && "admin".equals(requestingUser.getRole())) ?
                dao.findUserByLogin(requestedUserLogin) :
                dao.findEntityById(requestingUser.getId());
        dao.closeConnection();
        return user;
    }

    public List<Rent> getUserRents(User requestingUser, long requestedRentsUserId) {
        RentDao dao = new RentDao();
        List<Rent> rents;
        if (requestingUser.getId() != requestedRentsUserId && "admin".equals(requestingUser.getRole())) {
            rents = dao.userRents(requestedRentsUserId);
        } else {
            rents = dao.userRents(requestingUser.getId());
        }
        dao.closeConnection();
        return rents;
    }
}