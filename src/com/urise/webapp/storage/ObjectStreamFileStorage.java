package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serialize.Serialize;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ObjectStreamFileStorage extends AbstractFileStorage {
    private Serialize serialize;

    protected ObjectStreamFileStorage(File directory, Serialize serialize) {
        super(directory);
        this.serialize = serialize;
    }

    @Override
    protected void doWrite(Resume r, OutputStream os) throws IOException {
        serialize.doWrite(r, os);
    }

    @Override
    protected Resume doRead(InputStream is) throws IOException {
        return serialize.doRead(is);
    }
}
