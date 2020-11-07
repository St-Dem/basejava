package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public void saveResume(Resume resume, int index) {
        int abs = Math.abs(index) - 1;
        System.arraycopy(storage, abs, storage, abs + 1, size - abs);
        storage[abs] = resume;
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    protected void deleteResume(String uuid, int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }
}