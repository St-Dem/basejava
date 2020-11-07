package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    public void saveResume(Resume resume, int index) {
        storage[size] = resume;
    }

    protected void deleteResume(String uuid, int index) {
        storage[index] = storage[size - 1];
    }
}