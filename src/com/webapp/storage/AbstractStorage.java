package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract List<Resume> doCopyAll();

    public abstract void clear();


    public List<Resume> getAllSorted(){
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }

    public abstract int size();

    public void update(Resume r) {
        Object searchKey = getSearchKeyCheckNotExistedKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public void save(Resume r) {
        Object searchKey = getSearchKeyCheckExistedKey(r.getUuid());
        doSave(r, searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getSearchKeyCheckNotExistedKey(uuid);
        return doGet(searchKey);
    }
    @Override
    public void delete(String uuid) {
        Object searchKey = getSearchKeyCheckNotExistedKey(uuid);
        doDelete(searchKey);
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
