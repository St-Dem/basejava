package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorageInteger extends AbstractStorage {
    private int count = 0;
    private final Map<Integer, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
        count = 0;
    }

    protected void updateStorage(Resume resume, int index) {
        storage.put(index, resume);
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
        storage.put(index, storage.remove(--count));
    }

    public Resume getResume(int index, String uuid) {
        return storage.get(index);
    }

    protected void insertElement(Resume resume, int index) {
        storage.put(count++, resume);
    }

    protected int getIndex(String uuid) {
        for (Map.Entry<Integer, Resume> index : storage.entrySet()) {
            if (uuid.equals(index.getValue().getUuid())) {
                return index.getKey();
            }
        }
        return -1;
    }

}
