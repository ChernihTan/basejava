package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> map = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey((String) searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.put((String) searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put((String) searchKey, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get((String) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove((String) searchKey);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
   /* public Resume[] getAll() {
        // a sorted array must be returned, otherwise the test will fail
        List<Resume> list = new ArrayList<>();
        for (Map.Entry<String, Resume> enrty : map.entrySet()) {
            list.add(enrty.getValue());
        }
        Collections.sort(list);

        return list.toArray(new Resume[0]);
    }
*/
    public List<Resume> doCopyAll() {
        List<Resume> list = new ArrayList<>();
        for (Map.Entry<String, Resume> enrty : map.entrySet()) {
            list.add(enrty.getValue());
        }
        Collections.sort(list);
        return list;
    }
    @Override
    public int size() {
        return map.size();
    }
}
