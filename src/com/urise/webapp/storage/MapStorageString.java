package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorageString extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    protected void updateStorage(Resume resume, int index) {
        storage.put(resume.getUuid(), resume);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    public int size() {
        return storage.size();
    }

    protected void deleteResume(int index, String uuid) {
        storage.remove(uuid);
    }

    public Resume getResume(int index, String uuid) {
        return storage.get(uuid);
    }

    protected void insertElement(Resume resume, int index) {
        storage.put(resume.getUuid(), resume);
    }

    protected int getIndex(String uuid) {
        if (storage.containsKey(uuid)) {
            return 1;
        }
        return -1;
    }

}