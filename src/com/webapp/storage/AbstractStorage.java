package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void toUpdate(Resume r, Object searchKey);

    protected abstract void toSave(Resume r, Object searchKey);

    protected abstract Resume toGet(Object searchKey);

    protected abstract void toDelete(Object searchKey);

    public abstract void clear();

    public abstract Resume[] getAll();

    public abstract int size();

    public void update(Resume r) {
        Object searchKey = getSearchKeyCheckNotExistedKey(r.getUuid());
        toUpdate(r, searchKey);
    }

    public void save(Resume r) {
        Object searchKey = getSearchKeyCheckExistedKey(r.getUuid());
        toSave(r, searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getSearchKeyCheckNotExistedKey(uuid);
        return toGet(searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getSearchKeyCheckNotExistedKey(uuid);
        toDelete(searchKey);
    }

    private Object getSearchKeyCheckNotExistedKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getSearchKeyCheckExistedKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
