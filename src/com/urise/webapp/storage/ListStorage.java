package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected List<Resume> storage = new ArrayList<>();

    protected void clearStorage() {
        storage.clear();
    }

    protected void updateStorage(Resume resume, int index) {
        storage.set(index, resume);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return storage.toArray(new Resume[size]);
    }

    protected void deleteResume(int index) {
        storage.remove(index);
    }

    public Resume getResume(int index) {
        return storage.get(index);
    }

    protected void fillDeletedElement(int index) {
    }

    protected void insertElement(Resume resume, int index) {
        storage.add(size, resume);

    }

    protected int getIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

}

