package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private int size = 0;
    private final Map<Integer, Resume> storage = new TreeMap<>();

    public void clear() {
        storage.clear();
        size = 0;
    }

    protected void updateStorage(Resume resume, int index) {
        storage.put(index, resume);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[size]);
    }

    public int size() {
        return size;
    }

    protected void deleteResume(int index) {
        storage.remove(index);
        size--;
    }

    public Resume getResume(int index) {
        return storage.get(index);
    }

    protected void insertElement(Resume resume, int index) {
        storage.put(size++, resume);

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
