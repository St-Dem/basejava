package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorageInteger extends AbstractStorage<Integer> {
    private int count = 0;
    private final Map<Integer, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
        count = 0;
    }

    public int size() {
        return storage.size();
    }

    protected boolean isExist(Integer index) {
        return index > -1;
    }

    @Override
    protected void doSave(Resume r, Integer index) {
        storage.put(count++, r);
    }

    @Override
    protected Resume doGet(Integer index) {
        return storage.get(index);
    }

    @Override
    protected void doDelete(Integer index) {
        storage.put(index, storage.remove(--count));
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (Map.Entry<Integer, Resume> index : storage.entrySet()) {
            if (uuid.equals(index.getValue().getUuid())) {
                return index.getKey();
            }
        }
        return -1;
    }

    @Override
    protected void doUpdate(Resume r, Integer index) {
        storage.put(index, r);
    }
}