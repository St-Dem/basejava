package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(UUID_2);
        storage.update(resume);
        assertTrue(resume == storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void notExistStorage() throws Exception {
        Resume resume = new Resume("dummy");
        assertSame(resume, storage.get("dummy"));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] resume = storage.getAll();
        Assert.assertEquals(3, resume.length);
    }

    @Test
    public void save() throws Exception {
        storage.save(new Resume("dummy"));
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void existStorage() throws Exception {
        storage.save(new Resume(UUID_2));
    }

    @Test(expected = StorageException.class)
    public void storageException() {
        try {
            for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume(UUID.randomUUID().toString()));
            }
        } catch (Exception e) {
            fail("Test failed before storage overflow");
        }
        storage.save(new Resume("dummy"));
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_3);
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void notExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void get() throws Exception {
        assertEquals("uuid2", storage.get(UUID_2).toString());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}