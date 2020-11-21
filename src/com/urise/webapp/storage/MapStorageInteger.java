package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorageInteger extends AbstractStorage {
    private int count = 0;
    private final Map<Integer, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
        count = 0;
    }

    public List<Resume> copyAll() {
        return new ArrayList<>(storage.values());
    }

    public int size() {
        return storage.size();
    }

    protected void updateStorage(Resume resume, Object index) {
        storage.put((Integer) index, resume);
    }

    protected void insertElement(Resume resume, Object index) {
        storage.put(count++, resume);
    }

    protected void deleteResume(Object index) {
        storage.put((Integer) index, storage.remove(--count));
    }

    protected Resume getResume(Object index) {
        return storage.get(index);
    }

    protected boolean isExist(Object index) {
        return (Integer) index > -1;
    }

    protected Object getSearchKey(String uuid) {
        for (Map.Entry<Integer, Resume> index : storage.entrySet()) {
            if (uuid.equals(index.getValue().getUuid())) {
                return index.getKey();
            }
        }
        return -1;
    }
}