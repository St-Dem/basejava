package com.urise.webapp.storage;

import com.urise.webapp.storage.serialize.ObjectStreamSerializer;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new PathStorage(AbstractStorageTest.STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}