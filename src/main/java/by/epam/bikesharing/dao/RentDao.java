package by.epam.bikesharing.dao;

import by.epam.bikesharing.entity.Rent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RentDao extends AbstractDao {
    private static final String SELECT_ALL_RENTS = "SELECT * FROM dbo.[Rent]";
    private static final String SELECT_USER_RENTS = "SELECT * FROM dbo.[Rent] WHERE UserID=?";
    private static final String SELECT_CURRENT_RENT = "SELECT * FROM dbo.[Rent] WHERE UserID=? AND DateEnd IS NULL";
    private static final String DELETE_RENT = "DELETE FROM dbo.[Rent] WHERE RentID=?";
    private static final String INSERT_RENT = "INSERT INTO dbo.[Rent] (UserID, BikeID, DateStart) VALUES (?, ?, ?)";
    private static final String UPDATE_RENT = "UPDATE dbo.[Rent] SET DateEnd=?, TotalPrice=? WHERE RentId=?";

    @Override
    public List findAll() {
        return find(SELECT_ALL_RENTS);
//        List<Rent> rents = new ArrayList<>();
//        ResultSet resultSet = null;
//        try {
//            resultSet = getResultSet(SELECT_ALL_RENTS);
//            while (resultSet.next()) {
//                rents.add(getRentFromSet(resultSet));
//            }
//        } catch (SQLException e) {
//            System.err.println("SQL exception (request or table failed): " + e);
//        } finally {
//            close(resultSet);
//        }
//        return rents;
    }

    @Override
    public Object findEntityById(long id) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return delete(DELETE_RENT, id);
    }

    public Rent userCurrentRent(long userId) {
        List<Rent> rents = find(SELECT_CURRENT_RENT, Long.toString(userId));
        return rents.get(0);
//        ResultSet resultSet = null;
//        try {
//            resultSet = getResultSet(SELECT_CURRENT_RENT, Long.toString(userId));
//            if (resultSet.next()) {
//                return getRentFromSet(resultSet);
//            }
//        } catch (SQLException e) {
//            System.err.println("SQL exception (request or table failed): " + e);
//        } finally {
//            close(resultSet);
//        }
//        return null;
    }

    public List<Rent> userRents(long userId) {
        return find(SELECT_USER_RENTS, Long.toString(userId));
//        List<Rent> rents = new ArrayList<>();
//        ResultSet resultSet = null;
//        try {
//            resultSet = getResultSet(SELECT_USER_RENTS, Long.toString(userId));
//            while (resultSet.next()) {
//                rents.add(getRentFromSet(resultSet));
//            }
//        } catch (SQLException e) {
//            System.err.println("SQL exception (request or table failed): " + e);
//        } finally {
//            close(resultSet);
//        }
//        return rents;
    }

    private Rent getRentFromSet(ResultSet resultSet) throws SQLException {
        Rent rent = new Rent();
        rent.setId(resultSet.getLong("RentId"));
        rent.setUserId(resultSet.getLong("UserId"));
        rent.setBikeId(resultSet.getLong("BikeId"));
        rent.setStart(resultSet.getTimestamp("DateStart"));
        rent.setEnd(resultSet.getTimestamp("DateEnd"));
        rent.setCost(resultSet.getBigDecimal("TotalPrice"));
        return rent;
    }

    @Override
    PreparedStatement getCreateStatement(Object entity) throws SQLException {
        Rent rent = (Rent) entity;
        PreparedStatement statement = getPreparedStatement(INSERT_RENT);
        statement.setLong(1, rent.getUserId());
        statement.setLong(2, rent.getBikeId());
        statement.setTimestamp(3, rent.getStart());
        return statement;
    }

    @Override
    PreparedStatement getUpdateStatement(Object entity) throws SQLException {
        Rent rent = (Rent) entity;
        PreparedStatement statement = getPreparedStatement(UPDATE_RENT);
        statement.setTimestamp(1, rent.getEnd());
        statement.setBigDecimal(2, rent.getCost());
        statement.setLong(3, rent.getId());
        return statement;
    }

    @Override
    Object getEntityFromSet(ResultSet resultSet) throws SQLException {
        Rent rent = new Rent();
        rent.setId(resultSet.getLong("RentId"));
        rent.setUserId(resultSet.getLong("UserId"));
        rent.setBikeId(resultSet.getLong("BikeId"));
        rent.setStart(resultSet.getTimestamp("DateStart"));
        rent.setEnd(resultSet.getTimestamp("DateEnd"));
        rent.setCost(resultSet.getBigDecimal("TotalPrice"));
        return rent;
    }
}