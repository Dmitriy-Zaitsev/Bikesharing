package by.epam.bikesharing.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StringListDao extends AbstractDao {
    private static final String SELECT_BIKES_USER_NAMES =
        "SELECT Login FROM dbo.Bike LEFT JOIN dbo.[User] ON Bike.UserID = dbo.[User].UserID";
    private static final String SELECT_BIKES_SPOT_NAMES =
        "SELECT Address FROM dbo.Bike LEFT JOIN dbo.SharingSpot ON Bike.SpotID = SharingSpot.SpotID";

    @Override
    public List findAll() {
        return null;
    }

    public List<String> findBikesSpotNames() {
        List<String> spotNames = find(SELECT_BIKES_SPOT_NAMES);
        return spotNames;
    }

    public List<String> findBikesUserNames() {
        List<String> userNames = find(SELECT_BIKES_USER_NAMES);
        return userNames;
    }

    @Override
    public Object findEntityById(long id) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    PreparedStatement getCreateStatement(Object entity) throws SQLException {
        return null;
    }

    @Override
    PreparedStatement getUpdateStatement(Object entity) throws SQLException {
        return null;
    }

    @Override
    Object getEntityFromSet(ResultSet resultSet) throws SQLException {
        String result = resultSet.getString(1);
        return (result == null) ? "NULL" : result;
    }
}
