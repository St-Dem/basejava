package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;

import java.sql.Connection;
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
        } catch (SQLException e) {
            throw getStorageException(e);
        }
    }

    public <T> T transactionalExecute(SqlTransaction<T> executor) {
        try (Connection conn = connectionFactory.getConnection()) {
            try {
                conn.setAutoCommit(false);
                T res = executor.execute(conn);
                conn.commit();
                return res;
            } catch (SQLException e) {
                conn.rollback();
                throw getStorageException(e);
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    private PreparedStatement getPreparedStatement(String sqlConnection) throws SQLException {
        return connectionFactory.getConnection().prepareStatement(sqlConnection);
    }

    private StorageException getStorageException(SQLException e) {
        return "23505".equals(e.getSQLState())
                ? new ExistStorageException("Resume exist", e)
                : new StorageException(e);
    }
}
