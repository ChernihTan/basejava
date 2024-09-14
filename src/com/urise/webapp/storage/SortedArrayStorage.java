package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else if (size == 0) {
            storage[0] = r;
            size++;
        } else {
            int index = getIndex(r.getUuid());
            if (index > 0) {
                System.out.println("Resume " + r.getUuid() + " already exist");
            } else {
                index = -index - 1;
                System.arraycopy(storage, index, storage,
                        index + 1, size - index);
                storage[index] = r;
                size++;
            }
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            System.arraycopy(storage, index + 1, storage,
                    index, size - index - 1);
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
