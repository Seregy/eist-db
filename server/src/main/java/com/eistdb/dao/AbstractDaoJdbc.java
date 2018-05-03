package com.eistdb.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public abstract class AbstractDaoJdbc<T> {
    protected SQLServerDataSource dataSource;

    protected AbstractDaoJdbc(String username,
                              String password,
                              String serverName,
                              int port,
                              String dbName) {
        this.dataSource = new SQLServerDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setServerName(serverName);
        dataSource.setPortNumber(port);
        dataSource.setDatabaseName(dbName);
    }

    public abstract boolean update(T object);

    public boolean delete(final UUID id) {
        boolean result = false;
        try (Connection connection = getConnection()) {

            String sql = "DELETE FROM " + getTableName() + " WHERE id = ?";

            try (PreparedStatement statement =
                         connection.prepareStatement(sql)) {
                statement.setString(1, id.toString());
                statement.executeUpdate();
                result = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    protected abstract T getObjectFromResult(ResultSet resultSet)
            throws SQLException;

    protected abstract String getTableName();
}
