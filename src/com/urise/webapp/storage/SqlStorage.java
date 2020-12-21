package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.ConnectionHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    ConnectionHelper connectionHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionHelper = new ConnectionHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        connectionHelper.doExecute("DELETE FROM resume", (ConnectionHelper.VoidExecutor<PreparedStatement>) PreparedStatement::execute);
    }

    @Override
    public Resume get(String uuid) {
        return connectionHelper.doExecute("SELECT * FROM resume  WHERE uuid =?", ps -> {
            ps.setString(1, uuid);
            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, resultSet.getString("full_name"));
        });
    }

    @Override
    public void update(Resume r) {
        connectionHelper.doExecute("UPDATE resume SET full_name = ? WHERE uuid = ?", ps -> {
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getUuid());
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(r.getUuid());
            }
        });
    }

    @Override
    public void save(Resume r) {
        connectionHelper.doExecute("INSERT INTO resume (uuid, full_name) VALUES (?,?)", ps -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
        });
    }

    @Override
    public void delete(String uuid) {
        connectionHelper.doExecute("DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> arrayList = new ArrayList<>();
        return connectionHelper.doExecute("SELECT * FROM resume", ps -> {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                arrayList.add(new Resume(resultSet.getString("uuid").trim(), resultSet.getString("full_name").trim()));
            }
            return arrayList;
        });
    }

    @Override
    public int size() {
        return connectionHelper.doExecute("SELECT COUNT(*)  FROM resume", ps -> {
            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) {
                throw new NotExistStorageException("Tables not found");
            }
            return resultSet.getInt("count");
        });
    }
}
