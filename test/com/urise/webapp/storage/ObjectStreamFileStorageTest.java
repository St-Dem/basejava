package com.urise.webapp.storage;

import com.urise.webapp.storage.serialize.ObjectSerialize;

public class ObjectStreamFileStorageTest extends AbstractStorageTest {

    public ObjectStreamFileStorageTest() {
        super(new ObjectStreamFileStorage(AbstractStorageTest.STORAGE_DIR, new ObjectSerialize()));
    }
}