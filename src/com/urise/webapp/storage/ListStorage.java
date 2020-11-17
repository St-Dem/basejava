package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    protected void updateStorage(Resume resume, int index) {
        storage.set(index, resume);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
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
        storage.add(resume);

    }

    protected int getIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

}

