package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorageString extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    public int size() {
        return storage.size();
    }

    protected void updateStorage(Resume resume, Object uuid) {
        storage.put((String) uuid, resume);
    }

    protected void insertElement(Resume resume, Object uuid) {
        storage.put(resume.getUuid(), resume);
    }

    protected void deleteResume(Object uuid) {
        storage.remove(uuid);
    }

    protected Resume getResume(Object uuid) {
        return storage.get(uuid);
    }

    protected boolean isExist(Object uuid) {
        return uuid != null;
    }

    protected Object getSearchKey(String uuid) {
     return storage.containsKey(uuid) ?  uuid :  null;
    }
}