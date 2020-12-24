package com.urise.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " already exist", uuid);
    }

    public ExistStorageException(String message, Exception e) {
        super(message, null, e);
    }
}
