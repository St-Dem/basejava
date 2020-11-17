package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private int count = 0;
    private final Map<Integer, Resume> storage = new TreeMap<>();

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

    protected void deleteResume(int index) {
        storage.remove(index);
    }

    public Resume getResume(int index) {
        return storage.get(index);
    }

    protected void insertElement(Resume resume, int index) {
        storage.put(count++, resume);
        if (count == 2147483647) {
            List<Resume> arrayList = (List<Resume>) storage.values();
            storage.clear();
            count = 0;
            for (Resume r : arrayList) {
                storage.put(count++, r);
            }
        }
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
