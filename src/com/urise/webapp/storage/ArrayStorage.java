package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    /**
     * @return May return -1 if the specified identifier does not exist in the array objects
     */
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    protected void insertElement(Resume r, int Index) {
        storage[size] = r;
    }

    protected void fillDeletedElement(int index) {
        storage[index] = storage[size - 1];
    }
}