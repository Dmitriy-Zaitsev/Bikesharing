package by.epam.bikesharing.dao;

import by.epam.bikesharing.sqlpool.ConnectionPool;
import by.epam.bikesharing.sqlpool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> {
    static final Logger LOGGER = LogManager.getRootLogger();
    protected ProxyConnection connection;

    public abstract List<T> findAll();

    public abstract T findEntityById(long id);

    public abstract boolean delete(long id);

    public boolean create(Object entity) {
        PreparedStatement statement = null;
        try {
            statement = getCreateStatement(entity);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(String.format("SQL exception while trying to insert %s into DB: %s.", entity.toString(), e));
        } finally {
            close(statement);
        }
        return false;
    }

    public boolean update(Object entity) {
        PreparedStatement statement = null;
        try {
            statement = getUpdateStatement(entity);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(String.format("SQL exception while trying to update DB with %s: %s.", entity.toString(), e));
        } finally {
            close(statement);
        }
        return false;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("SQL exception while trying to close connection: %s.", e));
        }
    }

    abstract PreparedStatement getCreateStatement(Object entity) throws SQLException;

    abstract PreparedStatement getUpdateStatement(Object entity) throws SQLException;

    abstract Object getEntityFromSet(ResultSet resultSet) throws SQLException;

    List find(String sqlQuery, String ... params) {
        List<Object> entityList = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlQuery);
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entityList.add(getEntityFromSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("SQL exception while trying to %s: %s.", sqlQuery, e));
        } finally {
            close(statement);
        }
        return entityList;
    }

    boolean delete(String sqlQuery, long id) {
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlQuery);
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(String.format("SQL exception while trying to %s: %s.", sqlQuery, e));
        } finally {
            close(statement);
        }
        return false;
    }

    PreparedStatement getPreparedStatement(String query) throws SQLException {
        setConnection(ConnectionPool.INSTANCE.getConnection());
        return connection.prepareStatement(query);
    }

    void setConnection(ProxyConnection connection) {
        if (this.connection == null) {
            this.connection = connection;
        }
    }

    void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("SQL exception while trying to close statement: %s.", e));
        }
    }
}