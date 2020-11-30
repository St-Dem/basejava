package com.urise.webapp.storage;

import com.urise.webapp.storage.serialize.ObjectSerialize;

public class ObjectStreamPathStorageTest extends AbstractStorageTest{

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(AbstractStorageTest.STORAGE_DIR.getAbsolutePath(), new ObjectSerialize()));
    }
}