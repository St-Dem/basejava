package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public interface Storage {

    void clear() throws Exception;

    void update(Resume r);

    void save(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    Resume[] getAll() throws Exception;

    int size() throws Exception;
}

