package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;

import java.sql.Connection;
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
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlConnection)) {
            voidExecutor.execute(ps);
        } catch (Exception e) {
          exceptionHandler(e);
        }
    }

    public <T> T doExecute(String sqlConnection, Executor<T> executor) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlConnection)) {
            return executor.execute(ps);
        } catch (Exception e) {
            exceptionHandler(e);
            return null;
        }
    }

    private void exceptionHandler(Exception e){
        if (e instanceof SQLException) {
            throw new ExistStorageException("Resume exist");
        }else {
           throw new NotExistStorageException("Resume not exist");
        }
    }
}
