package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionHelper {
    public final ConnectionFactory connectionFactory;

    public ConnectionHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public interface Executor<T> {
        T execute(PreparedStatement ps) throws SQLException;
    }

    public interface VoidExecutor<T> {
        void execute(PreparedStatement ps) throws SQLException;
    }

    public void doExecute(String sqlConnection, VoidExecutor<PreparedStatement> voidExecutor) {
        try (PreparedStatement ps = getPreparedStatement(sqlConnection)) {
            voidExecutor.execute(ps);
        } catch (Exception e) {
            exceptionHandler(e);
        }
    }

    public <T> T doExecute(String sqlConnection, Executor<T> executor) {
        try (PreparedStatement ps = getPreparedStatement(sqlConnection)) {
            return executor.execute(ps);
        } catch (Exception e) {
            exceptionHandler(e);
            return null;
        }
    }

    private PreparedStatement getPreparedStatement(String sqlConnection) throws SQLException {
        return connectionFactory.getConnection().prepareStatement(sqlConnection);
    }

    private void exceptionHandler(Exception e) {
        if (e instanceof SQLException) {
            throw new ExistStorageException("Resume exist");
        } else {
            throw new NotExistStorageException("Resume not exist");
        }
    }
}
