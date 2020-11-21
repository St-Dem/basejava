package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected int size = 0;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected void updateStorage(Resume resume, Object index) {
        storage[(int) index] = resume;
    }

    protected void insertElement(Resume resume, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        saveElement(resume, (Integer) index);
        size++;
    }

    protected abstract void saveElement(Resume resume, int index);

    protected void deleteResume(Object index) {
        fillDeletedElement((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void fillDeletedElement(int index);

    protected Resume getResume(Object index) {
        return storage[(int) index];
    }

    protected boolean isExist(Object index) {
        return (Integer) index > -1;
    }

    protected abstract Object getSearchKey(String uuid);
}
