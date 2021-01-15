package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import com.urise.webapp.sql.ConnectionHelper;
import com.urise.webapp.util.JsonParser;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class SqlStorage implements Storage {
    ConnectionHelper connectionHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            Logger.getLogger(SqlStorage.class.getName()).info("SQL exception");
        }
        connectionHelper = new ConnectionHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        connectionHelper.doExecute("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public Resume get(String uuid) {
        return connectionHelper.transactionalExecute(conn -> {
            Resume r;
            try (PreparedStatement ps = conn.prepareStatement("" +
                    "SELECT uuid, full_name FROM resume r  WHERE r.uuid =? ")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                r = new Resume(uuid, rs.getString("full_name"));
            }
            addElement(conn, "SELECT type, value FROM contact c  WHERE c.resume_uuid =? ", r, true);
            addElement(conn, "SELECT type, value FROM section s  WHERE s.resume_uuid =? ", r, false);
            return r;
        });
    }

    @Override
    public void update(Resume r) {
        String uuid = r.getUuid();
        connectionHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume  SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, uuid);
                if (ps.executeUpdate() != 1) {
                    throw new NotExistStorageException(uuid);
                }
            }

            insertElement(conn, "DELETE FROM contact WHERE resume_uuid = ?", r, true);
            insertElement(conn, "DELETE FROM section WHERE resume_uuid = ?", r, false);
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        connectionHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
            }
            insertContact(r, conn);
            insertSection(r, conn);
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        connectionHelper.doExecute("DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return connectionHelper.transactionalExecute(conn -> {
            Map<String, Resume> resumeMap = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement("" +
                    "SELECT uuid, full_name FROM resume r " +
                    "ORDER BY full_name, uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    resumeMap.put(uuid, new Resume(uuid, rs.getString("full_name")));
                }
            }

            addElements(conn, "SELECT resume_uuid, type, value FROM contact c ", resumeMap, true);
            addElements(conn, "SELECT resume_uuid, type, value FROM section s ", resumeMap, false);
            return new ArrayList<>(resumeMap.values());
        });
    }

    @Override
    public int size() {
        return connectionHelper.doExecute("SELECT COUNT(*)  FROM resume", ps -> {
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next() ? resultSet.getInt("count") : 0;
        });
    }

    private void insertContact(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            Set<Map.Entry<ContactsType, String>> entries = r.getContacts().entrySet();
            if (entries.isEmpty()) {
                return;
            }
            for (Map.Entry<ContactsType, String> e : entries) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertSection(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, value) VALUES (?,?,?)")) {
            Set<Map.Entry<SectionType, AbstractSection>> entries = r.getSections().entrySet();
            if (entries.isEmpty()) {
                return;
            }
            for (Map.Entry<SectionType, AbstractSection> e : entries) {
                SectionType key = e.getKey();
                ps.setString(1, r.getUuid());
                ps.setString(2, key.name());
                AbstractSection value = e.getValue();
                ps.setString(3, JsonParser.write(value, AbstractSection.class));
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void addContacts(ResultSet rs, Resume r) throws SQLException {
        String type = rs.getString("type");
        if (type != null) {
            String value = rs.getString("value");
            r.addContacts(ContactsType.valueOf(type), value);
        }
    }

    private void addSections(ResultSet rs, Resume r) throws SQLException {
        String type = rs.getString("type");
        if (type != null) {
            String value = rs.getString("value");
            SectionType sectionType = SectionType.valueOf(type);
            // r.addSection(sectionType, readSection(sectionType, value));
            r.addSection(sectionType, JsonParser.read(value, AbstractSection.class));
        }
    }

    private void addElements(Connection conn, String command, Map<String, Resume> resumeMap, boolean bool) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(command)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String uuid = rs.getString("resume_uuid");
                if (bool) {
                    addContacts(rs, resumeMap.get(uuid));
                } else {
                    addSections(rs, resumeMap.get(uuid));
                }
            }
        }
    }

    private void addElement(Connection conn, String command, Resume r, boolean bool) throws SQLException {
        String uuid = r.getUuid();
        try (PreparedStatement ps = conn.prepareStatement(command)) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (bool) {
                    addContacts(rs, r);
                } else {
                    addSections(rs, r);
                }
            }
        }
    }

    private void insertElement(Connection conn, String command, Resume r, boolean bool) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(command)) {
            ps.setString(1, r.getUuid());
            ps.executeUpdate();
        }
        if (bool) {
            insertContact(r, conn);
        } else {
            insertSection(r, conn);
        }
    }
}

