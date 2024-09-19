package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    protected void insert(Resume r, int index) {
        int position = -index - 1;
        System.arraycopy(storage, position, storage,
                position + 1, size - position);
        storage[position] = r;
        size++;
    }

    @Override
    public void remove(int index) {
        System.arraycopy(storage, index + 1, storage,
                index, size - index - 1);
        storage[size - 1] = null;
        size--;
    }
}
