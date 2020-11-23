package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorageResume extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    public List<Resume> copyAll() {
        return new ArrayList<>(storage.values());
    }

    public int size() {
        return storage.size();
    }

    protected void updateStorage(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    protected void insertElement(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    protected void deleteResume(Object searchKey) {
        storage.remove(searchKey.toString());
    }

    protected Resume getResume(Object searchKey) {
        return (Resume) searchKey;
    }

    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    protected Object getSearchKey(String uuid) {
        return storage.getOrDefault(uuid, null);
    }
}