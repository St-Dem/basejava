package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    public int size() {
        return storage.size();
    }

    protected void updateStorage(Resume resume, Object index) {
        storage.set((Integer) index, resume);
    }

    protected void insertElement(Resume resume, Object index) {
        storage.add(resume);
    }

    protected void deleteResume(Object index) {
        storage.remove((int) index);
    }

    protected Resume getResume(Object index) {
        return storage.get((Integer) index);
    }

    protected boolean isExist(Object index) {
        return (Integer) index > -1;
    }

    protected Object getSearchKey(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }
}

