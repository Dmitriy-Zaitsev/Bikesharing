package by.epam.bikesharing.service.rent;

import by.epam.bikesharing.dao.RentDao;
import by.epam.bikesharing.entity.Rent;

import java.util.List;

public class UserRentsPageLogic {

    public List<Rent> getUserRents(long userId) {
        RentDao rentDAO = new RentDao();
        List<Rent> rents = rentDAO.userRents(userId);
        rentDAO.closeConnection();
        return rents;
    }
}