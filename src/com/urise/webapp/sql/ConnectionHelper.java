package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionHelper {
    public final ConnectionFactory connectionFactory;

    public ConnectionHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T doExecute(String sqlConnection, Executor<T> executor) {
        try (PreparedStatement ps = getPreparedStatement(sqlConnection)) {
            return executor.execute(ps);
        } catch (Exception e) {
            throw getStorageException(e);
        }
    }

    public interface Executor<T> {
        T execute(PreparedStatement ps) throws SQLException;
    }

    private PreparedStatement getPreparedStatement(String sqlConnection) throws SQLException {
        return connectionFactory.getConnection().prepareStatement(sqlConnection);
    }

    private StorageException getStorageException(Exception e) {
        if (e instanceof SQLException) {
            if ("23505".equals(((SQLException) e).getSQLState())) {
                return new ExistStorageException("Resume exist");
            }
        }
        if (e instanceof NotExistStorageException) {
            return (StorageException) e;
        }
        return new StorageException(e);
    }
}
