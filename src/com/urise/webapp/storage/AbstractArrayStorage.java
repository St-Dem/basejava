package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected Resume[] storage = new Resume[STORAGE_LIMIT];


    protected void clearStorage() {
        Arrays.fill(storage, 0, size, null);
    }

    public void updateStorage(Resume resume, int index) {
        storage[index] = resume;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }


    protected void deleteResume(int index) {
        storage[size - 1] = null;
    }

    public Resume getResume(int index) {
        return storage[index];
    }

}
