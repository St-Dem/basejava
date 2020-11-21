package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        updateStorage(resume, getExistedSearchKey(resume.getUuid()));
    }

    protected abstract void updateStorage(Resume resume, Object searchKey);

    public void save(Resume r) {
        insertElement(r, getNotExistedSearchKey(r.getUuid()));
    }

    protected abstract void insertElement(Resume r, Object searchKey);

    public void delete(String uuid) {
        deleteResume(getExistedSearchKey(uuid));
    }

    protected abstract void deleteResume(Object searchKey);

    public Resume get(String uuid) {
        return getResume(getExistedSearchKey(uuid));
    }

    protected abstract Resume getResume(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract Object getSearchKey(String uuid);

    public List<Resume> getAllSorted() {
        List<Resume> resumes = copyAll();
        Collections.sort(resumes);
        return resumes;
    }

    protected abstract List<Resume> copyAll();
}
