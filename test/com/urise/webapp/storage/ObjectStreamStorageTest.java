package com.urise.webapp.storage;

public class ObjectStreamStorageTest extends AbstractStorageTest{
    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(AbstractStorageTest.STORAGE_DIR));
    }
}